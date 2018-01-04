package com.spider;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by qd on 2016/3/24.
 */
public class Test {
    @org.junit.Test
    public void add() {

         RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
         CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        String html = null;
        HttpGet httpget = new HttpGet("http://qaboss.qdingnet.com/popularize-web/web/popularize/account/saveAccount?accountStatus=1&accountType=1&memberId=2c9080395259771801525d0e83e10025&projectId=76");// 以get方式请求该URL
        httpget.setHeader("Cookie","qa_jsid=a00a602a-6c82-477e-b7ea-380396ee60b6");
        try {
            HttpResponse responce = httpClient.execute(httpget);// 得到responce对象
            int resStatu = responce.getStatusLine().getStatusCode();// 返回码
            if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
                System.out.println(resStatu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpget.releaseConnection();
        }
    }
}
