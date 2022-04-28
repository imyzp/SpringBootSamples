package com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.annotation;

import com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.enums.UserRoleEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionCheck {
    UserRoleEnum[] roles() default {};
}
