package com.spider;


import com.spider.job.GainPageUrlJob;
import com.spider.job.IronJob;
import com.spider.service.DzInfoDataService;
import com.spider.service.DzInfoService;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/7/13 14:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext*.xml"})
public class Test2 extends TestCase {
    //
    @Autowired
    private IronJob ironJob;

    @Autowired
    private GainPageUrlJob gainPageUrlJob;

    @Autowired
    private DzInfoService dzInfoService;

    @Autowired
    private DzInfoDataService dzInfoDataService;



    @org.junit.Test
    public void testDojob() {
        try {

            //Map a = TemplateManage.template;
            //Map b = TemplateManage.templateType;
            //Map c = TemplateManage.templateType;

            //ironJob.doJob(null);

            gainPageUrlJob.getUrl(null);
            //Thread.sleep(100000);
            //new Th().a();

           /* ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(1));

            for(int i=0;i<10;i++){
                //ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
                MyTask myTask = new MyTask(i);
               executor.execute(myTask);
                System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
                }*/

        }
      /*  catch (ServiceException e) {
            e.printStackTrace();
        }*/
        catch (Exception e) {
            e.printStackTrace();
        }
    }





    //@Test
    public void testBase() {
        try {
            DzInfo dzInfo = new DzInfo();

            dzInfo.setCatid(19);
            dzInfo.setTypeid(9999);  // ？
            dzInfo.setTitle("?");    // 标题
            dzInfo.setStyle("");
            dzInfo.setThumb("");
            dzInfo.setKeywords("xx");   //关键字
            dzInfo.setDescription("xx");  // 摘要
            dzInfo.setPosids(false);

            dzInfo.setUrl("xx");
            dzInfo.setListorder(0);  // 排序 默认是0
            dzInfo.setStatus(99);  //  99 固定
            dzInfo.setSysadd(true);
            dzInfo.setIslink(true);
            dzInfo.setUsername("sys");
            int i = (int) (System.currentTimeMillis() / 1000);
            dzInfo.setInputtime((int)(System.currentTimeMillis() / 1000));
            dzInfo.setUpdatetime((int)(System.currentTimeMillis() / 1000));
            dzInfo.setType1(""); //保留字段一 （如果是市场分析就包括，日报周报月报，如果是价格汇总就不分，如果是价格行情就可能是铜精矿，铜管，铜棒之类的）
            dzInfo.setType2(""); // 保留字段


            dzInfoService.save(dzInfo);



            DzInfoData dzInfoData = new DzInfoData();
            dzInfoData.setId(dzInfo.getId());
            dzInfoData.setContent("aaa");   // 内容
            dzInfoData.setGroupidsView(""); //?
            dzInfoData.setTemplate("模板A");
            dzInfoData.setTemplateUrl("钢材模板A");
            dzInfoData.setRelation("");
            dzInfoData.setMetaltype("钢");  // 金属类别  metaltype    下面有四个分类（钢材，铜，铝，镍，其他有色）
            dzInfoData.setMsgtype("市场行情");   // msgtype 资讯类型    分为四类（比如钢材行情，市场分析，库存统计，价格汇总）
            //dzInfoDataService.save(dzInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(2000);
                System.out.println("正在执行task " + taskNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }
}