package com.spider.job;

import com.google.common.collect.Maps;
import com.spider.SpiderUrl;
import com.spider.SpiderUrlPage;
import com.spider.exception.ServiceException;
import com.spider.job.param.GetUrlPar;
import com.spider.job.param.JobPar;
import com.spider.job.param.ScheduleResponse;
import com.spider.service.SpiderHostService;
import com.spider.service.SpiderUrlPageService;
import com.spider.service.SpiderUrlService;
import com.spider.util.HandlePageUrl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by qd on 2016/3/24.
 */
@Log4j
@Service("gainPageUrlJob")
public class GainPageUrlJob extends BaseAbstractJob {

    @Resource(name = "spiderUrlPageService")
    private SpiderUrlPageService spiderUrlPageService;


    @Resource(name = "spiderUrlService")
    private SpiderUrlService spiderUrlService;

    @Autowired
    private HandlePageUrl handlePageUrl;

    @Autowired
    private JobT jobT;

    @Autowired
    private SpiderHostService spiderHostService;

    private static long ns = 1000;//一秒钟的毫秒数
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");


    /**
     * 获取所有页的URL信息
     *
     * @param par
     * @return
     * @throws ServiceException
     */
    @Override
    public List<String> getUrl(GetUrlPar par) throws ServiceException, ParseException, InterruptedException {
        SpiderUrlPage urlPage = new SpiderUrlPage();
        urlPage.setUrl("http://list1.mysteel.com/market/p-221-----0101-0--------1.html");
        //spiderUrlPageService.save(urlPage);

        // 最外层是 spring 调度  每天查询入口

        //  然后定时任务

        // 入口入库

        // 定时库  解析（标记） 入库
       /* log.info("gain-url任务开始：" + sdf.format(new Date()));
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new JobTask(), 1, 1, TimeUnit.MILLISECONDS);

        log.info("任务结束：" + sdf.format(new Date()));*/
        //Thread t2=new Thread(new JobTask());
        //t2.start();
        //t2.join();

        //Thread.sleep(100000);
        return null;
    }

    @Override
    public List<String> analyzePage(GetUrlPar par) throws ServiceException {
        return null;
    }

    //@PostConstruct
    public void initDoJob() throws ServiceException, InterruptedException {
        doJob(null);
    }

    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 4, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2));

    @Override
    public ScheduleResponse doJob(JobPar jobPar) throws ServiceException, InterruptedException {
       /* log.info("gain-url任务开始：" + sdf.format(new Date()));
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new JobTask(), 5, 5, TimeUnit.SECONDS);*/


        //while(true){
        //ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
        if (executor.getQueue().size() >= 4) {
            return null;
            //Thread.sleep(3000);
            //continue;
        }
        Thread.sleep(1000);
        //JobT myTask = new JobT();
        JobTask myTask2 = new JobTask();
        //new Thread(jobT).start();
        executor.execute(myTask2);
        log.info("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());

        //}

        return null;
    }

    public  void startRun() {

        Map<String, Object> map = Maps.newConcurrentMap();
        map.put("statusRecursion", 1);
        List<SpiderUrlPage> list = spiderUrlPageService.query(map);
        long maxNum;
        for (SpiderUrlPage urlPage : list) {
            maxNum = urlPage.getRunNumberMax();
            //  modify时 忽略以下两个属性
            urlPage.setStatusRecursion(null);
            urlPage.setRunNumberMax(null);
            String cookie = spiderHostService.findById(urlPage.getHostId()).getCookieStr();
            String html = getHtmlByUrl(urlPage.getUrlPostion(), cookie);
            List<SpiderUrl> urls = parseUrls(html, "div#content>* div#articleList>ul.nlist> * a");
            String nextPageUrl = nextPageUrl("http://list1.mysteel.com/", html, "div#content>* div#articleList>div.page>a", "下一页");

            // 更新url
            for (SpiderUrl url : urls) {
                Map<String, Object> urlMap = Maps.newConcurrentMap();
                urlMap.put("url", url.getUrl());

                String s = "http://anhui.mysteel.com/p/16/0330/10/D26542D401DC0B83.html";
                if (url.getUrl().equals("http://anhui.mysteel.com/p/16/0330/10/D26542D401DC0B83.html")) {
                    log.info("--------");
                }
                synchronized ((url.getUrl()+"").intern()){
                    if (spiderUrlService.count(urlMap) == 0) {
                        try {
                            url.setCreateAt(System.currentTimeMillis());
                            url.setShowUrl(urlPage.getUrlPostion());
                            url.setHostId(urlPage.getHostId());
                            url.setType1(urlPage.getTypeA());
                            url.setType2(urlPage.getTypeB());
                            url.setType3(urlPage.getTypeC());
                            url.setType4(urlPage.getTypeD());
                            spiderUrlService.save(url);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


            // 如果已经翻页达到 RunNumberMax  或者 不存在nextpage
            if (nextPageUrl == null || urlPage.getRunNumber() >= maxNum) {
                //
                urlPage.setRunNumber(0);
                urlPage.setUrlPostion(urlPage.getUrl());


                spiderUrlPageService.modifySelective(urlPage);
                continue;
            }
            //  更新 urlpage
            urlPage.setRunNumber(urlPage.getRunNumber() + 1);
            urlPage.setUrlPostion(nextPageUrl);
            spiderUrlPageService.modifySelective(urlPage);





            /*    try {
                    if(url!=null)
                        handlePageUrl.handleUrl(urlPage,url);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }*/
        }
    }


    class JobTask implements Runnable {


        @Override
        public void run() {
            log.info("gain-sub-url开始:");
            startRun();
            log.info("gain-sub-url结束:" );
        }
    }

}
