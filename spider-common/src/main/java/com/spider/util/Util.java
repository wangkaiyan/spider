package com.spider.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by qd on 2016/1/9.
 */
public class Util {


    public  static String handleURI(String url) {


        int index = url.indexOf("?");
        String domain = url.substring(0, index);
        System.out.println(domain);
        System.out.println(url.substring(index +1));

        String [] params = url.substring(index +1).split("&");
        StringBuffer buffer = new StringBuffer();
        for(String p: params) {
            String[] ips = p.split("=");
            try {
                buffer.append(ips[0]).append("=").append(URLEncoder.encode(ips[1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        if (buffer.length() > 0 && buffer.toString().endsWith("&")) {
            buffer.setLength(buffer.length() - 1); // 删除最后一个&符号
        }
        String lastParam = buffer.toString();
        System.out.println(lastParam);

        return domain + "?" + lastParam;
    }

    public static  String getHostUrl(String url){
        String[] urlArr = url.split("/");
        StringBuffer host = new StringBuffer("http://");
        host.append(urlArr[2]);
        return host.toString();
    }

    public static void main(String[] args) {
        String s="http://lv.myyouse.com/p/07/0605/17/846082A2415F0902.html";
        System.out.print(getHostUrl(s));
    }


}
