package com.spider.job;

/**
 * Created by qd on 2016/3/30.
 */
public class Job2 {

    private static int i = 0;

    public void doJob2() {

        System.out.println("不继承QuartzJobBean方式-调度" + ++i + "进行中...");
    }
}