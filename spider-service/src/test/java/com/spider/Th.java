package com.spider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by qd on 2016/3/24.
 */
public class Th {

    public static void main(String[] args) {
        new Th().a();
    }


    public void a(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new JobTask(), 1, 1, TimeUnit.MILLISECONDS);
    }



    class JobTask implements Runnable  {
        @Override
        public void run() {
            System.out.println("---");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
