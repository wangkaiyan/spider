package com.spider;


import com.spider.msg.service.MsgService;
import com.spider.service.SpiderUrlPageService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/7/13 14:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext*.xml"})
public class Test1 extends TestCase {

    @Autowired
    private MsgService msgService;

    @Resource(name = "spiderUrlPageService")
    private SpiderUrlPageService spiderUrlPageService;

    @Test
    public void add() {
        //Msg msg = msgService.findById("5ac6b969b90311e58b3e00163e001208");
        //System.out.println(msg.getId());
        SpiderUrlPage urlPage = spiderUrlPageService.findById("qqqqqqqq");
        //spiderUrlPageService.modifySelective(urlPage);
        //spiderUrlPageService.modify(urlPage);
        //System.out.println("----");

    }
  /*  @Before
    public void setUp() throws Exception {

    }

    @Test1
    public void add() throws ParseException {

    }

    @Test1
    public void query() throws ParseException {

    }*/
}