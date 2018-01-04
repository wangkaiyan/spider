package com.spider;

import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * Created by qd on 2016/3/25.
 */
public class TestCleaner {

    RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
    CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();


    public static void main(String[] args) {
        //String html = "<div id=\"sss\"><script></script><div id=\"sss\">xxx</div><span>ss</span><img src=\"http://a.mysteelcdn.com/common/3.0/logo12560.png\" width=\"125\" height=\"60\" border=\"0\" /><a href=\"http://member1.mysteel.com/\" target=\"_blank\" >sss</a><div id=\"specDiv\" class=\"open_select_box\">xxx</div></div>";

        TestCleaner t=new TestCleaner();

        String html=t.getHtmlByUrl("http://jiancai.mysteel.com/m/16/0325/08/57A520E70E2B63DD.html","_login_token=91f400fb17ba9276b56f33dde31d5fee; _login_uid=1760842; _login_mid=2594445; _last_loginuname=zjdz588; 91f400fb17ba9276b56f33dde31d5fee=1%3D10%26catalog%3D0201%2C0205;");


        //Whitelist whitelist = Whitelist.relaxed();//创建relaxed的过滤器
        Whitelist whitelist =Whitelist.relaxed();
        Document doc = Jsoup.parseBodyFragment(html);
        whitelist.addTags("div");//添加relaxed里面没有的标签
        //whitelist.removeTags("script");
        whitelist.addAttributes(":all",  "id", "name","href","target","border");
        //whitelist.addAttributes("a", "href", "class");
        Cleaner cleaner = new Cleaner(whitelist);
        doc = cleaner.clean(doc);//按照过滤器清除
//doc.outputSettings().prettyPrint(false);//是否格式化


        //String html = getHtmlByUrl(url, cookieStr);
        Map<String,String> ruleMap = Maps.newConcurrentMap();
        ruleMap.put("first_article","div#main>div#articleContent>div#text>div#first_article");
        ruleMap.put("textContent","div#main>div#articleContent>div#text>div#text22222");
        ruleMap.put("title","div#main>div#articleContent>h1");
        ruleMap.put("root","div#main>div#articleContent>div#text");

        String first_article ="";
        String textContent ="";
        String title;


        Element textEle = null;

        for (Map.Entry<String, String> entry : ruleMap.entrySet()) {
            Elements elements ;
            if(entry.getKey() .equals("first_article")){
                elements =doc.select(entry.getValue());
                if(elements.size()>0) {
                     first_article = elements.get(0).toString();
                    System.out.println(first_article);
                }
            }else if(entry.getKey() .equals("textContent")){
                elements =doc.select(entry.getValue());
                if(elements.size()>0) {
                    Element textContentEle= elements.get(0);
                    textContentEle.getElementById("company-rec").remove();
                     textContent = textContentEle.toString();
                    System.out.println(textContent);
                }
            }else if (entry.getKey() .equals("title")){
                elements =doc.select(entry.getValue());
                if(elements.size()>0) {
                     title = elements.get(0).html();
                    System.out.println(title);
                }
            }else if (entry.getKey() .equals("all")){
                elements =doc.select(entry.getValue());
                if(elements.size()>0) {
                    textEle =  elements.get(0).html("");
                    System.out.println(textEle);
                }
            }
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }
        textEle.append(first_article);
        textEle.append(textContent);

        System.out.println(textEle);//完整的html字符串
        System.out.println(textEle);//完整的html字符串
    }






    public  String getHtmlByUrl(String url,String cookieStr) {
        String html = null;
        HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
        httpget.setHeader("Cookie",cookieStr);
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
}
