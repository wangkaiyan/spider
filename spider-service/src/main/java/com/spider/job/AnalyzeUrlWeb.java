package com.spider.job;

import com.google.common.collect.Maps;
import com.spider.DzInfo;
import com.spider.DzInfoData;
import com.spider.SpiderUrl;
import com.spider.exception.ServiceException;
import com.spider.job.param.GetUrlPar;
import com.spider.job.param.JobPar;
import com.spider.job.param.ParseVo;
import com.spider.job.param.ScheduleResponse;
import com.spider.service.DzInfoDataService;
import com.spider.service.DzInfoService;
import com.spider.service.SpiderHostService;
import com.spider.service.SpiderUrlService;
import com.spider.util.CleanerUtil;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by qd on 2016/3/27.
 */
@Log4j
@Service("analyzeUrlWeb")
public class AnalyzeUrlWeb extends  BaseAbstractJob {


    @Resource(name = "spiderUrlService")
    private SpiderUrlService spiderUrlService;


    @Resource(name = "spiderHostService")
    private SpiderHostService spiderHostService;


    @Resource(name = "dzInfoService")
    private DzInfoService dzInfoService;


    @Resource(name = "dzInfoDataService")
    private DzInfoDataService dzInfoDataService;

   /* @Resource(name = "templateManage")
    private TemplateManage templateManage;
*/



    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(40));

    @Override
    public List<String> getUrl(GetUrlPar par) throws ServiceException, ParseException, InterruptedException {
        return null;
    }

    @Override
    public List<String> analyzePage(GetUrlPar par) throws ServiceException {
        return null;
    }

    //@PostConstruct
    public void initDoJob() throws ServiceException {
        try {
            doJob(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ScheduleResponse doJob(JobPar jobPar) throws ServiceException, InterruptedException {
        int i=0;

        //while(true){
            //ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
            if(executor.getQueue().size()>=5){
                Thread.sleep(1000);
                //continue;
            }
            MyTaskUrl myTask = new MyTaskUrl(i++);
            executor.execute(myTask);
            log.info("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        return  null;
        //}
    }

    class MyTaskUrl implements Runnable {
        private int taskNum;
        public MyTaskUrl() {}

        public MyTaskUrl(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            try {
                startRun();
                log.info("正在执行task " + taskNum);
            } catch (ServiceException e) {
                log.info("错误码-"+e.getReturnInfo().getCode()+"msg-"+e.getReturnInfo().getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("task " + taskNum + "执行完毕");
        }


        private void startRun() throws ServiceException, ParseException {
            //处理上架   order by rand() limit 1


            Map<String, Object> map = Maps.newConcurrentMap();
            map.put("status", 0);
            SpiderUrl url = spiderUrlService.selectByRand(map);
            if(url == null)
                return;
            synchronized ((url.getId() + "").intern()) {

                url = spiderUrlService.findById(url.getId());
                if(url.getStatus() != 0 ){
                    return;
                }
                String cookie = spiderHostService.findById(url.getHostId()).getCookieStr();
                String html = getHtmlByUrl(url.getUrl(), cookie);

                Document doc = Jsoup.parseBodyFragment(html);
                doc = CleanerUtil.cleaner.clean(doc);

                //Document doc = Jsoup.parse(html);
                //  区分模板类型
                //模板A  钢1
                String templateType =  templateManage.guessTemplateType(doc);
                ParseVo parseVo = getTextElement(doc, templateType);
                DzInfo dzInfo = new DzInfo(parseVo.getTitle(), parseVo.getTitle(), parseVo.getTitle(), url.getUrl(), url.getType1(), url.getType2(),url.getType3(),url.getType4());
                dzInfo.setType2(templateType);

                dzInfoService.save(dzInfo);

                DzInfoData dzInfoData = new DzInfoData(dzInfo.getId(), parseVo.getElement().toString(), url.getType1(), url.getType2(), templateType);


                dzInfoDataService.save(dzInfoData);

                url.setStatus(1);
                spiderUrlService.modifySelective(url);
            }







        }
    }
}
