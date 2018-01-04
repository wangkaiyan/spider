package com.spider.job;

import com.spider.SpiderHost;
import com.spider.service.SpiderHostService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import org.apache.http.client.methods.HttpGet;

/**
 * Created by qd on 2016/1/9.
 */
@Log4j
@Service("loginUtil")
public class LoginUtil {

    private    String msgCookieStr =null;

    private   RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
    private   CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();


    @Autowired
    private SpiderHostService spiderHostService;

    @Autowired
    private LoginByHtmlUnit loginByHtmlUnit;


    public   String refreshCookie() {
        log.info("开始refreshCookie");
        try {
            String id="aaa";
            SpiderHost host = spiderHostService.findById(id);
             boolean b= testLogin(host.getCookieStr(), host);
            if(!b){
                String cookie = loginByHtmlUnit.login(host);
                host.setCookieStr(cookie);
                spiderHostService.modify(host);
                //String cookie = login(host);
                if(StringUtils.isNotEmpty(cookie)){
                    host.setCookieStr(cookie);
                    spiderHostService.modify(host);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("refreshCookie-异常"+e.getMessage());
        }
        return  "";

    }


    public   String getCookieById(String id) throws IOException {

        SpiderHost host = spiderHostService.findById(id);

        return  host.getCookieStr();

    }

    public   String login( SpiderHost host) throws IOException {
        List<NameValuePair> params = new LinkedList<NameValuePair>();

        params.add(new BasicNameValuePair("my_username", host.getUsername()));
        params.add(new BasicNameValuePair("my_password", host.getPassword()));
        params.add(new BasicNameValuePair("site", "mysteel"));
        //site:mysteel
        //callback:http://www.myyouse.com/
        params.add(new BasicNameValuePair("callback", "http://www.myyouse.com/"));
        //params.add(new BasicNameValuePair("cookietime", cookietime));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);

        //创建一个post请求
        HttpPost post = new HttpPost(host.getLoginUrl());
        //post.setHeader("Cookie", " cap_id=\"YjA5MjE0YzYyNGQ2NDY5NWJhMmFhN2YyY2EwODIwZjQ=|1437610072|e7cc307c0d2fe2ee84fd3ceb7f83d298156e37e0\"; ");
        post.releaseConnection();
        //注入post数据
        post.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(post);
        Map<String,String> ssoCookieMap = setCookie(httpResponse);

        msgCookieStr = cookieMap4String(ssoCookieMap);
        //打印登录是否成功信息
        //printResponse(httpResponse);
        return msgCookieStr;
    }



    // 通过验证是否可以打开个人中心的方式  是否302
    public   boolean testLogin(String msgCookieStr,SpiderHost host) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build(); //setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间

        //  此处为了阻止302跳转
        System.out.println("-------***-----------");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(host.getVerifyUrl());
        HttpParams params = httpClient.getParams();;
        params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        //httpget.setConfig(requestConfig);
        //httpget.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        httpget.setHeader("Cookie", msgCookieStr);
        HttpResponse response = httpClient.execute(httpget);
        System.out.println("++++++++++++++++++"+response.getStatusLine().getStatusCode());
        if( response.getStatusLine().getStatusCode()  == 302){
            return false;
        }else  if(response.getStatusLine().getStatusCode()  == 200){
            return  true;
        }else{
            return  false;
        }

      /* 以下解决不了302问题
      RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build(); //setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
        HttpGet g = new HttpGet(host.getVerifyUrl());
        g.setConfig(requestConfig);   //  禁止302*/
         /* g.setHeader("Cookie", msgCookieStr);
        System.out.println("-------***-----------");
        HttpResponse r = httpClient.execute(g);
        System.out.println("++++++++++++++++++"+r.getStatusLine().getStatusCode());
        if( r.getStatusLine().getStatusCode()  == 302){
            return false;
        }else  if(r.getStatusLine().getStatusCode()  == 200){
            return  true;
        }else{
            return  false;
        }*/
        //return  true;
    }


    public  String getMsgCookieStr()
    {
        try {

            if(msgCookieStr == null) {
                //login();
               /* crossdomain_all();
                crossdomain1();
                crossdomain2();*/
                //TestLogin();
                return msgCookieStr;
            }else{
                return msgCookieStr;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        } finally {
            try {
                //httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private   void printResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        System.out.println("status:" + httpResponse.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            System.out.println("response length:" + responseString.length());
            System.out.println("response content:"
                    + responseString.replace("\r\n", ""));
        }
    }


    //从响应信息中获取cookie
    private  Map<String,String> setCookie(HttpResponse httpResponse)
    {
        Map<String,String> ssoCookieMap = new HashMap<String, String>(64);
        System.out.println("----setCookieStore");
        Header headers[] = httpResponse.getHeaders("Set-Cookie");
        if (headers == null || headers.length==0)
        {
            System.out.println("----there are no cookies");
        }

        for (Header header:headers){
            for(HeaderElement headerElement: header.getElements()){
                ssoCookieMap.put(headerElement.getName(),headerElement.getValue());
            }
        }

        return ssoCookieMap;
    }

    //从响应信息中获取cookie
    private  String getCookieString(HttpResponse httpResponse)
    {
        System.out.println("----setCookieStore");
        Header headers[] = httpResponse.getHeaders("Set-Cookie");
        if (headers == null || headers.length==0)
        {
            System.out.println("----there are no cookies");
        }
        Map<String,String> tempCookieMap = new HashMap<String, String>(64);
        for (Header header:headers){
            for(HeaderElement headerElement: header.getElements()){
                tempCookieMap.put(headerElement.getName(),headerElement.getValue());
            }
        }
        return cookieMap4String(tempCookieMap);

    }

    private  String cookieMap4String (Map<String,String> map){
        String cookieString ="";
        String spit ="";
        for (String key :map.keySet())
        {
            spit = ";";
            //_login_uid=1760842; _login_mid=2594445; _last_loginuname
            if("_login_token".equals(key)||"_login_uid".equals(key)||"_login_mid".equals(key)||"_last_loginuname".equals(key)||"074da3e81fce5a407b9e35c853c18ef6".equals(key)) {
                cookieString += key + "=" + map.get(key) + spit;
                spit = ";";
            }
        }
        return  cookieString;
    }
}