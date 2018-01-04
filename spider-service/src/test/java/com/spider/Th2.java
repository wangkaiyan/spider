package com.spider;

import com.spider.exception.ServiceException;
import com.spider.job.GainPageUrlJob;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by qd on 2016/3/24.
 */
public class Th2 {

    public static void main(String[] args) throws ParseException, InterruptedException, ServiceException {
        ApplicationContext context = new ClassPathXmlApplicationContext(" classpath*:/applicationContext*.xml");
        GainPageUrlJob gainPageUrlJob=(GainPageUrlJob)context.getBean("gainPageUrlJob");
        gainPageUrlJob.doJob(null);

        //new Th2().a();
    }

    @org.junit.Test
    public void a(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(Integer.MAX_VALUE));

        for(int i=0;i<30;i++){
            //ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
    }



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
