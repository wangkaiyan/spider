package com.spider.job;

import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/1/9.
 */
public class MyyouseLogin {

    private  static  String msgCookieStr =null;

    private  static RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
    private  static CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
   // private  static HttpClient httpClient = new DefaultHttpClient();
    private static Map<String,String> ssoCookieMap = new HashMap<String, String>(64);
    private  static  String ssoCookieStr ="";

    public static  boolean login() throws IOException {
        List<NameValuePair> params = new LinkedList<NameValuePair>();

        params.add(new BasicNameValuePair("my_username", "zjdz588"));
        params.add(new BasicNameValuePair("my_password", "111111"));
        params.add(new BasicNameValuePair("site", "mysteel"));
        //site:mysteel
        //callback:http://www.myyouse.com/
        params.add(new BasicNameValuePair("callback", "http://www.myyouse.com/"));
        //params.add(new BasicNameValuePair("cookietime", cookietime));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);

        //创建一个post请求
        HttpPost post = new HttpPost("http://passport.mysteel.com/login.jsp");
        //post.setHeader("Cookie", " cap_id=\"YjA5MjE0YzYyNGQ2NDY5NWJhMmFhN2YyY2EwODIwZjQ=|1437610072|e7cc307c0d2fe2ee84fd3ceb7f83d298156e37e0\"; ");
        post.releaseConnection();
        //注入post数据
        post.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(post);
        setCookie(httpResponse);
        msgCookieStr = cookieMap4String(ssoCookieMap);
        //打印登录是否成功信息
        //printResponse(httpResponse);
        return true;
    }

    public static  boolean crossdomain_all() throws IOException {
        // iframe sso
        // /sso/crossdomain_all.jsp?act=login&callback=http://www.myyouse.com
        // http://passport.mysteel.com
        String hostUrl = "http://passport.mysteel.com";
        String subUrl = "/sso/crossdomain_all.jsp?act=login&callback=http://www.myyouse.com";
        HttpGet crossdomainGet = new HttpGet(hostUrl+subUrl);

        crossdomainGet.setHeader("Cookie",ssoCookieStr);
        HttpResponse re = httpClient.execute(crossdomainGet);
        String crossdomainContent = EntityUtils.toString(re.getEntity());
        System.out.println(crossdomainContent);
        crossdomainGet.releaseConnection();
        return true;
    }

    public static  boolean crossdomain1() throws IOException {
        // sso  1  sososteel.com
        //http://passport.mysteel.com/sso/crossdomain.jsp?act=login&domain=myyouse.com
        HttpGet ssoGet = new HttpGet("http://passport.mysteel.com/sso/crossdomain.jsp?act=login&domain=sososteel.com");
        ssoGet.setHeader("Cookie", ssoCookieStr);
        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build(); //setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
        //RequestConfig.custom().set
        // 禁止get 302 跳转
        ssoGet.setConfig(requestConfig);
        //HttpParams hparams = httpClient.getParams();
        //hparams.setParameter(ClientPNames.HANDLE_REDIRECTS, false);

        HttpResponse reSso = httpClient.execute(ssoGet);
        ssoGet.releaseConnection();
        //ssoGet.abort();
        //set cookie 并无用处
       /* Header[] headers =reSso.getHeaders("Location");
        String setCookieUrl = headers[0].getValue();
        String setCookieUrlNew = Util.handleURI(setCookieUrl);
        HttpGet setCookie = new HttpGet(setCookieUrlNew);
        setCookie.releaseConnection();
        HttpResponse rsetCookie = httpClient.execute(ssoGet);
        String reSsoContent = EntityUtils.toString(rsetCookie.getEntity());
        System.out.println(reSsoContent);*/

        return true;
    }
    public static  boolean crossdomain2() throws IOException {
        // sso  2  myyouse.com
        //http://passport.mysteel.com/sso/crossdomain.jsp?act=login&domain=myyouse.com
        HttpGet ssoGet = new HttpGet("http://passport.mysteel.com/sso/crossdomain.jsp?act=login&domain=myyouse.com");
        ssoGet.setHeader("Cookie", ssoCookieStr);
        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build(); //setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
        //RequestConfig.custom().set
        // 禁止get 302 跳转
        ssoGet.setConfig(requestConfig);
        HttpResponse reSso = httpClient.execute(ssoGet);
        //ssoGet2.abort();
        //set cookie 并无用处
       /* Header[] headers =reSso.getHeaders("Location");
        String setCookieUrl = headers[0].getValue();
        String setCookieUrlNew = Util.handleURI(setCookieUrl);
        HttpGet setCookie = new HttpGet(setCookieUrlNew);
        HttpResponse rsetCookie = httpClient.execute(setCookie);
        String reSsoContent = EntityUtils.toString(rsetCookie.getEntity());
        System.out.println(reSsoContent);*/
        ssoGet.releaseConnection();
        return true;
    }

    public static  boolean TestLogin() throws IOException {
        //
        //HttpParams hparams = httpClient.getParams();
        //hparams.setParameter(ClientPNames.HANDLE_REDIRECTS, true);
        // setCookie  302
        //http://passport.mysteel.com/sso/crossdomain.jsp?act=login&domain=myyouse.com
           /* HttpGet ssoGet = new HttpGet(hostUrl+subUrl);
            String ssoCook = setCookie(re);
            crossdomainGet.setHeader("Cookie",ssoCook);
            CloseableHttpResponse reSso = httpClient.execute(ssoGet);
            String reSsoContent = EntityUtils.toString(reSso.getEntity());
            System.out.println(reSsoContent);*/



        //构造一个get请求，用来测试登录cookie是否拿到
        HttpGet g = new HttpGet("http://tong.myyouse.com/p/16/0316/11/CD05C46D06D17D93.html");
        //http://nie.myyouse.com/p/16/0105/11/019623519FCEF08C.html
        //得到post请求返回的cookie信息
        //String c = setCookie(httpResponse);
        //将cookie注入到get请求头当中
        //g.setHeader("Cookie","pgv_pvi=7655515136; pgv_si=s3516535808; _login_token=b09785f82cf372f9305906849a0d3590; _login_mid=2598710; _login_uid=1765341; _last_loginuname=yousewang11; b09785f82cf372f9305906849a0d3590=35%3D5%2636%3D5%2615%3D5%2633%3D5%2616%3D5%2634%3D5%2613%3D5%2614%3D5%2637%3D5%2638%3D5%2642%3D5%2641%3D5%2632%3D5%264%3D5%2631%3D5%26catalog%3D010205%2C010202%2C0222%2C0223; bdshare_firstime=1452347986036; Hm_lvt_fb0daa6f924ccbb475eb07ac0fa6f020=1452323433,1452336888,1452337968,1452343196; Hm_lpvt_fb0daa6f924ccbb475eb07ac0fa6f020=1452347989");
        g.setHeader("Cookie",msgCookieStr);
        HttpResponse r = httpClient.execute(g);
        //String content = EntityUtils.toString(r.getEntity());
        String postResult = EntityUtils.toString(r.getEntity(), "GBK");
        System.out.println(postResult);

        return true;
    }



    public static String getMsgCookieStr()
    {
        try {

            if(msgCookieStr == null) {
                login();
                crossdomain_all();
                crossdomain1();
                crossdomain2();
                TestLogin();
                return msgCookieStr;
            }else{
                return msgCookieStr;
            }

        } catch (IOException e1) {
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

    private  static void printResponse(HttpResponse httpResponse)
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
    private static void setCookie(HttpResponse httpResponse)
    {
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

    }

    //从响应信息中获取cookie
    private static String getCookieString(HttpResponse httpResponse)
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

    private static String cookieMap4String (Map<String,String> map){
        String cookieString ="";
        String spit ="";
        for (String key :map.keySet())
        {
            cookieString +=key+"="+map.get(key)+spit;
            spit = ";";
        }
        return  cookieString;
    }
}