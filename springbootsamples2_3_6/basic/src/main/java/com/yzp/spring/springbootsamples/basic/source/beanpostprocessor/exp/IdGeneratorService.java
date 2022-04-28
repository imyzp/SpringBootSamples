package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.exp;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 定义 ID 生成器
 * @Author yaozhenpeng
 * @Time 2022/4/29 0:03
 */
public interface IdGeneratorService {
    String groupName();

    long nextId();
}
