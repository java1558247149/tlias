package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class MyAspect01 {
    //@Before("execution(* com.itheima.service.impl.ClazzServiceImpl.deleteClazz(java.lang.Integer))")
    //@Before("execution(* *.*.*.*.*.de*(*))")
    //@Before("@annotation(com.itheima.anno.LogOperation)")

    public void testAspect(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        log.info("目标对象为，{}", target);
        String name = joinPoint.getTarget().getClass().getName();
        log.info("类名为，{}", name);
        Object[] args = joinPoint.getArgs();
        log.info("参数为，{}", Arrays.toString(args));
        Signature signature = joinPoint.getSignature();
        log.info("方法名为，{}", signature);
    }

}
