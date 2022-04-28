package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.exp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description
 * 前面都是属于基本操作，这里才是扩展的核心。我们的实现逻辑是：
 * 扫描 bean 的所有属性，然后找到定义了IdGeneratorClient注解的属性
 * 获取注解的value值，作为 ID 生成器的分组标识
 * 使用IdGeneratorFactory这个工厂类生成 ID 生成器实例，这里会返回新建的或已经定义的实例
 * 通过反射将 ID 生成器实例写入 bean
 * @Author yaozhenpeng
 * @Time 2022/4/29 0:05
 */
public class IdGeneratorBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        parseFields(bean);
        return bean;
    }

    private void parseFields(final Object bean) {
        if (bean == null) {
            return;
        }
        Class<?> clazz = bean.getClass();
        parseFields(bean, clazz);

        while (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
            clazz = clazz.getSuperclass();
            parseFields(bean, clazz);
        }
    }

    private void parseFields(final Object bean, Class<?> clazz) {
        if (bean == null || clazz == null) {
            return;
        }

        for (final Field field : clazz.getDeclaredFields()) {
            try {
                final IdGeneratorClient annotation = AnnotationUtils.getAnnotation(field, IdGeneratorClient.class);
                if (annotation == null) {
                    continue;
                }

                final String groupName = annotation.value();

                final Class<?> fieldType = field.getType();
                if (fieldType.equals(IdGeneratorService.class)) {
                    final IdGeneratorService idGenerator = IdGeneratorFactory.INSTANCE.create(groupName);
                    invokeSetField(bean, field, idGenerator);
                    continue;
                }

                throw new RuntimeException("未知字段类型无法初始化，bean: " + bean + "，field: " + field);
            } catch (Throwable t) {
                throw new RuntimeException("初始化字段失败，bean=" + bean + "，field=" + field, t);
            }
        }
    }

    private void invokeSetField(final Object bean, final Field field, final Object param) {
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, bean, param);
    }
}
