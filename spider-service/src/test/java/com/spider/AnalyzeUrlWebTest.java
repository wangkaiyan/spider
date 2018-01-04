package com.spider;

import com.spider.exception.ServiceException;
import com.spider.job.AnalyzeUrlWeb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;

/**
 * Created by qd on 2016/3/27.
 */
public class AnalyzeUrlWebTest {


    public static void main(String[] args) throws ParseException, InterruptedException, ServiceException {
        ApplicationContext context = new ClassPathXmlApplicationContext(" classpath*:/applicationContext*.xml");
        AnalyzeUrlWeb analyzeUrlWeb=(AnalyzeUrlWeb)context.getBean("analyzeUrlWeb");
        analyzeUrlWeb.doJob(null);

        //new Th2().a();
    }
}
