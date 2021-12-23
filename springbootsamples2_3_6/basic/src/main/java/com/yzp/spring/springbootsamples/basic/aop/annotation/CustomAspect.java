package com.yzp.spring.springbootsamples.basic.aop.annotation;

import com.yzp.spring.springbootsamples.basic.aop.model.UserAop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CustomAspect {
    /**
     * @annotation 作用在方法上
     */
    @Pointcut(value = "@annotation(com.yzp.spring.springbootsamples.basic.aop.annotation.CustomAnnotation)")
    public void pointCut(){

    }
    @Before("pointCut() && @annotation(customAnnotation)")
    public void before(CustomAnnotation customAnnotation){

    }

    /**
     * 定义了Aop切面拦截我们的方法上是否有加上自定义的注解
     * @param joinPoint
     */
    @Around(value = "pointCut() && @annotation(customAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint,CustomAnnotation customAnnotation) throws Throwable {
        System.out.println("被aop拦截");
        String description = customAnnotation.description();
        int value = customAnnotation.value();
        String name = customAnnotation.name();
        System.out.println("description--------"+description);
        System.out.println("value--------"+value);
        System.out.println("name--------"+name);
        Object[] args = joinPoint.getArgs();
        for(Object o: args)
        {
            System.out.println("参数："+o);
        }
        // joinPoint 表示被CustomAnnotation修饰的目标方法
        UserAop result = (UserAop) joinPoint.proceed();
        System.out.println("被aop拦截的方法体执行结束");
        return result;
    }
}
