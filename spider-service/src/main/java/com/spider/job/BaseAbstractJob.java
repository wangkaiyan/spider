package com.spider.job;

import com.spider.SpiderUrl;
import com.spider.exception.ServiceException;
import com.spider.job.param.ParseVo;
import com.spider.util.ConstantsUtil;
import com.spider.util.TemplateManage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/3/24.
 */
public abstract class BaseAbstractJob implements BaseJob {

    private RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
    private CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();


    @Resource(name = "templateManage")
    protected TemplateManage templateManage;

    public String nextPageUrl(String host,String html,String rule,String keyword){
        String href = null;
        Document doc = Jsoup.parse(html);
        Element element =doc.select(rule).last();
        if(keyword.equals(element.html())){
            href = element.attr("href");
            return host + href;
        }else{
            return null;
        }
    }


    public  String getHtmlByUrl(String url,String cookieStr) {
        String html = null;

        HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
        httpget.setHeader("Cookie",cookieStr);

        HttpPost httpPost =   new HttpPost(url);// 以post方式请求该URL
        httpPost.setHeader("Cookie",cookieStr);


        try {
            HttpResponse responce = httpClient.execute(httpget);// 得到responce对象
            int resStatu = responce.getStatusLine().getStatusCode();// 返回码
            if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
                // 获得相应实体
                HttpEntity entity = responce.getEntity();
                if (entity != null) {
                    html = EntityUtils.toString(entity, "GBK");
                    //html = new String(content.getBytes("ISO-8859-1"),"GBK");
                    //解决HttpClient获取中文乱码 ，用String对象进行转码
                    //System.out.println(content);
                }
            }
        } catch (Exception e) {
            System.out.println("访问【" + url + "】出现异常!");
            e.printStackTrace();
        } finally {
            httpget.releaseConnection();
        }
        return html;
    }


    public List<SpiderUrl> parseUrls(String html,String rule){
        List<SpiderUrl> list = new ArrayList<SpiderUrl>();
        Document doc = Jsoup.parse(html);
        Elements links =doc.select(rule);
        for (Element ele : links) {
            String href = ele.attr("href");
            String title = ele.text();
            System.out.println(href + "," + title);
            SpiderUrl url = new SpiderUrl();
            url.setUrl(href.trim());
            url.setTitile(title);
            list.add(url);
        }
        return  list;

    }

    public ParseVo getTextElement(Document doc,String templateType) throws ServiceException{
        ParseVo parseVo = new ParseVo();

        //  内存复制一份
        Document tempdoc = doc.clone();
        String first_article = "";
        String textContent = "";
        String title = "";
        Element textEle = null;
        if(!ConstantsUtil.UN_KNOWN_TEMPLATE.equals(templateType)){
            Map<String,String> template = templateManage.getTemplate().get(templateType);
            for (Map.Entry<String, String> entry : template.entrySet()) {
                Elements elements ;
                if(entry.getKey() .equals("first_article")){
                    elements =doc.select(entry.getValue());
                    if(elements.size()>0) {
                        first_article = elements.get(0).toString();
                        //System.out.println(first_article);
                    }
                }else if(entry.getKey() .equals("textContent")){
                    elements =doc.select(entry.getValue());
                    if(elements.size()>0) {


                        Element textContentEle= elements.get(0);
                        // 去掉最后一个P
                        Elements  temp =  doc.select(entry.getValue()+">p");
                        int pl = temp.size();
                        if(pl>0){
                            temp.get(pl-1).remove();
                        }


                        textContentEle.getElementById("company-rec").remove();
                        textContentEle.select("p").remove();
                        textContent = textContentEle.toString();
                        //System.out.println(textContent);
                    }
                }
                else if(entry.getKey() .equals("textContent_b")){
                    elements =doc.select(entry.getValue());
                    if(elements.size()>0) {
                        Element textContentEle= elements.get(0);
                        // 去掉最后一个P
                        Elements  temp =  doc.select(entry.getValue()+">p");
                        int pl = temp.size();
                        if(pl>0){
                            temp.get(pl-1).remove();
                        }
                        textContent = textContentEle.html();
                        //System.out.println(textContent);
                    }
                }
                else if(entry.getKey() .equals("textContent_c")){
                    elements =doc.select(entry.getValue());
                    if(elements.size()>0) {
                        //Element textContentEle= elements.text();
                        textContent = elements.toString();
                        //System.out.println(textContent);
                    }
                }
                else if (entry.getKey() .equals("title")){
                    elements =doc.select(entry.getValue());
                    if(elements.size()>0) {
                        title = elements.get(0).html();
                        //System.out.println(title);
                    }
                }
                else if (entry.getKey() .equals("root")){
                    elements =tempdoc.select(entry.getValue());
                    if(elements.size()>0) {
                        textEle =  elements.get(0).html("");
                        //System.out.println(textEle);
                    }
                }
            }
            if(textEle == null){
                throw new ServiceException(403,"root-error-cookie-error");
            }
            textEle.append(first_article);
            textEle.append(textContent);
        }else{
            throw new ServiceException(400,"templateType-error");

        }



        parseVo.setElement(textEle);
        parseVo.setTitle(title);

        return  parseVo;
    }
}
