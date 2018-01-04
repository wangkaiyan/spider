package com.spider;

import com.spider.exception.ServiceException;
import com.spider.job.LoginUtil;
import com.spider.service.SpiderHostService;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by qd on 2016/3/27.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:/applicationContext*.xml"})
public class TestLogin extends TestCase {

    @Autowired
    private LoginUtil loginUtil;


    @Autowired
    private SpiderHostService spiderHostService;

    @org.junit.Test
    public void tt(){
        loginUtil.refreshCookie();
    }


    public static void main(String[] args) throws ParseException, InterruptedException, ServiceException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(" classpath*:/applicationContext*.xml");
        LoginUtil loginUtil=(LoginUtil)context.getBean("loginUtil");
        SpiderHostService spiderHostService=(SpiderHostService)context.getBean("spiderHostService");

        String id="aaa";
        SpiderHost host = spiderHostService.findById(id);
        //SpiderHost host = spiderHostService.findById(id);
        boolean  b = loginUtil.testLogin(host.getCookieStr(), host);
        System.out.println(b);
        //loginUtil.refreshCookie();
        //analyzeUrlWeb.doJob(null);

        //new Th2().a();
    }
}
