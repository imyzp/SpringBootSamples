package com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.annotation;

import com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.enums.UserRoleEnum;
import com.yzp.spring.springbootsamples.basic.usage.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(value = Integer.MIN_VALUE +10)
public class PermissionCheckAspect {
    @Pointcut(value = "@within(com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.annotation.PermissionCheck) || @annotation(com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.annotation.PermissionCheck)")
    public void pointcut(){

    }
    @Before("pointcut()")
    public void beforeExecute(JoinPoint joinPoint){
        String loginUserId = "1";// 具体需要根据实际获取
        if(StringUtils.isEmpty(loginUserId)){
            throw new CustomException("无权限操作");
        }
        // 获取类上有注解的
        PermissionCheck action = joinPoint.getTarget().getClass().getAnnotation(PermissionCheck.class);
        if(Objects.isNull(action)){
            // 获取方法上有注解的
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            action = method.getAnnotation(PermissionCheck.class);
        }
        if(action != null){
            UserRoleEnum[] roles = action.roles();
            Set<String> requiredRoleSet = Arrays.stream(roles).map(UserRoleEnum::getCode).collect(Collectors.toSet());
            // 获取当前登录用户角色(具体需要根据实际获取)
            Set<String> userRoleSet = new HashSet<>();
            userRoleSet.add("0");
            boolean accessAllowed = userRoleSet.containsAll(requiredRoleSet) || CollectionUtils.containsAny(requiredRoleSet, userRoleSet);
            if(!accessAllowed)
            {
                throw new CustomException("无权限操作");
            }
        }
    }
}
