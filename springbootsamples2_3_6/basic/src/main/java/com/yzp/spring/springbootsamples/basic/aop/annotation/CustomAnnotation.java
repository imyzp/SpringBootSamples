package com.yzp.spring.springbootsamples.basic.aop.annotation;

import java.lang.annotation.*;

/**
 * ElementType.METHOD 作用在方法上
 * 自定义一个注解，然后通过aop技术来生效注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomAnnotation {
    String name() default "";
    int value() default 0;
    String description() default "";
}
