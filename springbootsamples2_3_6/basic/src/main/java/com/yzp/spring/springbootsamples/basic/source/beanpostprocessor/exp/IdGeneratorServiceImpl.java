package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.exp;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 实现 ID 生成器接口，偷懒使用AtomicLong实现自增，同时考虑 ID 生成器是分组的，通过ConcurrentHashMap实现 ID 生成器的持有
 * @Author yaozhenpeng
 * @Time 2022/4/29 0:04
 */
public class IdGeneratorServiceImpl implements IdGeneratorService {
    private static final Map<String, AtomicLong> ID_CACHE = new ConcurrentHashMap<>(new HashMap<>());
    private final String groupName;

    IdGeneratorServiceImpl(final String groupName) {
        this.groupName = groupName;
        synchronized (ID_CACHE) {
            ID_CACHE.computeIfAbsent(groupName, key -> new AtomicLong(1));
        }
    }

    @Override
    public String groupName() {
        return this.groupName;
    }

    @Override
    public long nextId() {
        return ID_CACHE.get(this.groupName).getAndIncrement();
    }
}
