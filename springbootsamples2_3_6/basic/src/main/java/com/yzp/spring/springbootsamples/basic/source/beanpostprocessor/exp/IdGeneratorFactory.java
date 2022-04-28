package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.exp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 我们需要一个工厂类来创建 ID 生成器
 * @Author yaozhenpeng
 * @Time 2022/4/29 0:05
 */
public enum IdGeneratorFactory {
    INSTANCE;

    private static final Map<String, IdGeneratorService> ID_GENERATOR_MAP = new ConcurrentHashMap<>(new HashMap<>());

    public synchronized IdGeneratorService create(final String groupName) {
        return ID_GENERATOR_MAP.computeIfAbsent(groupName, key -> new IdGeneratorServiceImpl(groupName));
    }
}
