package com.yzp.spring.springbootsamples.basic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CustomAop {
    /**
     * 定义了Aop切面拦截我们的方法上是否有加上自定义的注解
     * @param joinPoint
     */
    @Around(value = "@annotation(com.yzp.spring.springbootsamples.basic.aop.CustomAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("被aop拦截");
        // joinPoint 表示被CustomAnnotation修饰的目标方法
        Object result = joinPoint.proceed();
        System.out.println("被aop拦截的方法体执行结束");
        return result;
    }
}
