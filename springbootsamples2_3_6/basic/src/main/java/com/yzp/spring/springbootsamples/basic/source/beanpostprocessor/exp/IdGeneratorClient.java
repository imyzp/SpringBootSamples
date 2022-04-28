package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.exp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 自定义一个注解，可以定义一个value属性，作为隔离业务的标识：
 * @Author yaozhenpeng
 * @Time 2022/4/29 0:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IdGeneratorClient {
    /**
     * ID 生成器名称
     *
     * @return
     */
    String value() default "DEFAULT";
}
