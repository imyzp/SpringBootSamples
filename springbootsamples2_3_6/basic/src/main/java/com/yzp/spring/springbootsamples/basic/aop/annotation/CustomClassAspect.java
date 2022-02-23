package com.yzp.spring.springbootsamples.basic.aop.annotation;

import com.yzp.spring.springbootsamples.basic.aop.model.UserAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Component
@Aspect
@Order(value = Integer.MIN_VALUE +10)// 加10为了防止报错 https://blog.csdn.net/weixin_30834019/article/details/101360990
public class CustomClassAspect {
    /**
     * @within 作用在类上
     * execution 排除不需要拦截的方法
     */
    @Pointcut(value = "@within(com.yzp.spring.springbootsamples.basic.aop.annotation.CustomClassAnnotation) && " +
            "!execution(* com.yzp.spring.springbootsamples.basic.aop.api.AopClassController.exclude())")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        // 获取url参数到map
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, Object> parameterMap = getParameterMap(request);
    }
    private Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<?, ?> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();

        Map.Entry<String, Object> entry;
        String name = "";
        String value = "";
        Object valueObj =null;
        while (entries.hasNext()) {
            entry = (Map.Entry<String, Object>) entries.next();
            name = (String) entry.getKey();
            valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
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
