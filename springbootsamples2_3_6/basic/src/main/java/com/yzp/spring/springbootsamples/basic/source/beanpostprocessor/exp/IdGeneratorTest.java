package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.exp;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 目前运行不成功，参考地https://blog.csdn.net/wg22222222/article/details/122879915
 *   对于Class类的用法掌握不好需要看看
 * @Author yaozhenpeng
 * @Time 2022/4/29 0:06
 */
@SpringBootTest
public class IdGeneratorTest {
    @IdGeneratorClient
    private IdGeneratorService defaultIdGenerator;
    @IdGeneratorClient("group1")
    private IdGeneratorService group1IdGenerator;

    @Test
    public void contextLoads() {
        Assert.notNull(defaultIdGenerator, "注入失败");
        System.out.println(defaultIdGenerator.groupName() + " => " + defaultIdGenerator.nextId());

        Assert.notNull(group1IdGenerator, "注入失败");
        for (int i = 0; i < 5; i++) {
            System.out.println(defaultIdGenerator.groupName() + " => " + defaultIdGenerator.nextId());
            System.out.println(group1IdGenerator.groupName() + " => " + group1IdGenerator.nextId());
        }
    }
}
