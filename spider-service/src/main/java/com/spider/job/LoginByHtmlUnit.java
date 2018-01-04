package com.spider.job;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.spider.SpiderHost;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by qd on 2016/4/5.
 */
@Service("loginByHtmlUnit")
public class LoginByHtmlUnit {

    public String login(SpiderHost host) throws IOException {
        // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
        WebClient webclient = new WebClient();

        // 这里是配置一下不加载css和javaScript,配置起来很简单，是不是
        webclient.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        webclient.getOptions().setCssEnabled(true);
        webclient.getOptions().setJavaScriptEnabled(true);
        webclient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webclient.getOptions().setThrowExceptionOnScriptError(false);

        // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
        //HtmlPage htmlpage = webclient.getPage("http://news.baidu.com/advanced_news.html");
        HtmlPage htmlpage = webclient.getPage(host.getVerifyUrl());

        // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
        //login.jsp
        List<HtmlForm> forms =  htmlpage.getForms();

        HtmlForm form = forms.get(0);
        // 同样道理，获取”百度一下“这个按钮
        HtmlTextInput username = (HtmlTextInput)form.getInputByName("my_username");
        HtmlPasswordInput password = form.getInputByName("my_password");
        username.type(host.getUsername());
        password.type(host.getPassword());
        HtmlImageInput button
                = (HtmlImageInput) form.getInputByName("imageField");
        //form.getButtonByName("")
        //Page page = button.click();
        HtmlPage nextPage = (HtmlPage) button.click();

        CookieManager CM = webclient.getCookieManager(); //WC = Your WebClient's name
        Set<Cookie> cookies_ret = CM.getCookies();
        Iterator<Cookie> it = cookies_ret.iterator();
        String login_token = "";
        while (it.hasNext()) {
            Cookie str = it.next();
            String name=str.getName();
            if("_login_token".equals(name)) {
                login_token = name+"="+str.getValue()+";";
            }
        }
        String result = nextPage.asXml();
        System.out.println(result);

        return login_token;

    }



}
