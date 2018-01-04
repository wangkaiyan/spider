package com.spider;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by qd on 2016/3/30.
 */
@Aspect
public class LogPointcut {

    @Pointcut("execution(* com.demo.service..*.*(..))" )
    public void inServiceLayer() { }

}