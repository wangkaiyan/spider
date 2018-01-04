package com.spider;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by qd on 2016/3/30.
 */
@Aspect
public class LogTest {

    @Before(value = "execution(public * com.spider.base.impl..*.*(..))" )
    public void beforeShow() {
        System. out.println("before show." );
    }

    @After(value = "execution(public * com.spider.base.impl..*.*(..))" )
    public void afterShow() {
        System. out.println("after show." );
    }
}
