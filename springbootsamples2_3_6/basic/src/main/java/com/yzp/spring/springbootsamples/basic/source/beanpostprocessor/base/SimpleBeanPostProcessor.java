package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 实现类中只是简单的打印下bean的信息以及beanName
 * @Author yaozhenpeng
 * @Time 2022/4/28 23:25
 */
@Component
public class SimpleBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization - " + bean.getClass().getName() + " - " + beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization  - " + bean.getClass().getName() + " - " + beanName);
        return null;
    }
}
