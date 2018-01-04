package com.spider.job;

import com.spider.DzInfo;
import com.spider.DzInfoData;
import com.spider.exception.ServiceException;
import com.spider.job.param.*;
import com.spider.service.DzInfoDataService;
import com.spider.service.DzInfoService;
import com.spider.util.CleanerUtil;
import com.spider.util.Util;
import lombok.extern.log4j.Log4j;
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
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/3/21.
 */
@Log4j
@Service("ironJob")
public class IronJob extends BaseAbstractJob {

    private RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
    private CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    public List<LinkVo> urls = new ArrayList<LinkVo>();
    public int pageNo = 0;
    public String cookieStr = "_login_token=91f400fb17ba9276b56f33dde31d5fee; _login_uid=1760842; _login_mid=2594445; _last_loginuname=zjdz588; 91f400fb17ba9276b56f33dde31d5fee=1%3D10%26catalog%3D0201%2C0205;";


    @Resource(name = "dzInfoService")
    private DzInfoService dzInfoService;


    @Resource(name = "dzInfoDataService")
    private DzInfoDataService dzInfoDataService;

    @Override
    public List<String> getUrl(GetUrlPar par) throws ServiceException {
        homeTag("http://list1.mysteel.com/market/p-221-----0101-0--------1.html",cookieStr);
        return null;
    }

    @Override
    public List<String> analyzePage(GetUrlPar par) throws ServiceException {
        return null;
    }

    @Override
    public ScheduleResponse doJob(JobPar jobPar) throws ServiceException {

        String url = "";
        String cook = "";

        //getUrl(null);
        LinkVo linkVo = new LinkVo("http://jiancai.mysteel.com//m/16/0325/11/13947CE4C4D08414.html","t","价格汇总");
        urls.add(linkVo);

            for(LinkVo link : urls){
                String pageHtml = getHtmlByUrl(link.getUrl(), cookieStr);
                //parseSubPage(pageHtml, "div#main> * div#text");
                try {

                    DzInfo dzInfo = parsePage4Save(pageHtml,null, link);
                    log.info(dzInfo.getId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }catch (ServiceException e) {
                    e.printStackTrace();
                }
               /* msg.setCategory(link.getCategory());
                msg.setChannel(link.getChannel());
                msg.setDoc_url(link.getUrl());
                msgDao.insert(msg);*/


            }

        return null;
    }




    public DzInfo parsePage4Save(String html ,Map<String,String> ruleMap,LinkVo link) throws ParseException, ServiceException {

        Document doc = Jsoup.parseBodyFragment(html);

        doc = CleanerUtil.baseClean(doc);

        //Document doc = Jsoup.parse(html);
        //  区分模板类型
        //模板A  钢1
        String templateType =  templateManage.guessTemplateType(doc);
        ParseVo parseVo = this.getTextElement(doc,templateType);
        DzInfo dzInfo = new  DzInfo(parseVo.getTitle(), "钢材",parseVo.getTitle(),link.getUrl(),"钢材","","",templateType);
        dzInfo.setType2(templateType);

        dzInfoService.save(dzInfo);

        DzInfoData dzInfoData = new DzInfoData(dzInfo.getId(),parseVo.getElement().toString(),"钢","市场行情","");


        dzInfoDataService.save(dzInfoData);
        return dzInfo;

    }


    public void  homeTag(String href,String cookieStr){
        String html = getHtmlByUrl(href,cookieStr);
        List<LinkVo> pageUrls = parseMsgUrls(html, "div#content>* div#articleList>ul.nlist> * a");

        String nextUrl =  nextPageUrl(Util.getHostUrl(href),html, "div#content>* div#articleList>div.page>a");
        if(nextUrl == null){
            return;
        }
        pageNo ++;
        urls.addAll(pageUrls);
        //递归

        System.out.println("递归次数"+pageNo);
        if (pageNo == 1){
            return;
        }
        homeTag(nextUrl, cookieStr);

    }
    public String nextPageUrl(String host,String html,String rule){
        String href = null;
        Document doc = Jsoup.parse(html);
        Element element =doc.select(rule).last();
        if("下一页".equals(element.html())){
            href = element.attr("href");
            return host + href;
        }else{
            return null;
        }
    }

    public List<LinkVo> parseMsgUrls(String html,String rule){
        List<LinkVo> list = new ArrayList<LinkVo>();
        Document doc = Jsoup.parse(html);
        Elements links =doc.select(rule);
        for (Element ele : links) {
            String href = ele.attr("href");
            String title = ele.text();
            System.out.println(href + "," + title);
            LinkVo linkVo = new LinkVo(href,"t","价格汇总");
            list.add(linkVo);
        }
        return  list;

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
