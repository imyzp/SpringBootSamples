package com.yzp.spring.springbootsamples.basic.aop.annotation;

import com.yzp.spring.springbootsamples.basic.aop.model.UserAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CustomClassAspect {
    /**
     * @within 作用在类上
     */
    @Pointcut(value = "@within(com.yzp.spring.springbootsamples.basic.aop.annotation.CustomClassAnnotation)")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("adfa");

    }

    /**
     * 环绕拦截
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("被aop拦截");
        // joinPoint 表示被CustomAnnotation修饰的目标方法
        UserAop result = (UserAop) joinPoint.proceed();
        System.out.println("被aop拦截的方法体执行结束");
        return result;
    }
}
