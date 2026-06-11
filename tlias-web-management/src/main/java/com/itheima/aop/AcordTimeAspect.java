package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class AcordTimeAspect {
    //@Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt(){};
    //@Around("pt()")
   public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
       long start = System.currentTimeMillis();
       Object result = pjp.proceed();
       long end = System.currentTimeMillis();
       log.info("方法执行耗时：{}", end - start);
       return  result;
   }
   @Before("pt()")
   public void before() throws Throwable {
       log.info("before");
   }
    @After("pt()")
    public void after() throws Throwable {
        log.info("after");
    }
    @AfterReturning("pt()")
    public void afterReturning() throws Throwable {
        log.info("afterReturning");
    }
    @AfterThrowing("pt()")
    public void afterThrowing() throws Throwable {
        log.info("AfterThrowing");
    }
}
