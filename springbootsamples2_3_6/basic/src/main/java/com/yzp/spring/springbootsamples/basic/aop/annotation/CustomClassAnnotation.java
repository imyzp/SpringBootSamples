package com.yzp.spring.springbootsamples.basic.aop.annotation;

import java.lang.annotation.*;

/**
 * ElementType.TYPE 作用在类上
 * 自定义一个注解，然后通过aop技术来生效注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomClassAnnotation {
    String name() default "";
    int value() default 0;
    String description() default "";
}
