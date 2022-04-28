package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.base;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 想要后置处理器起作用，那就必须启动spring容器。我们使用AnnotationConfigApplicationContext来启动spring容器。并且在spring中注入一个自定义Bean myBean
 * @Author yaozhenpeng
 * @Time 2022/4/28 23:39
 */
@ComponentScan("com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.base")
@Configuration("spring.postProcessor.run")
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Test.class);
        applicationContext.close();
        // 运行结果
//        MyBean constructor
//        postProcessBeforeInitialization - com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.simpleuse.MyBean - myBean
//        MyBean is init
//        postProcessAfterInitialization  - com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.simpleuse.MyBean - myBean
//        从打打印结果可以看出，后置处理器在bean的构造方法执行之后执行。而且后置处理器的方法postProcessBeforeInitialization和postProcessAfterInitialization分别在Bean的init方法前后执行。
//        并且后置处理器会对spring中所有的bean起作用。
//        在spring源码类org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory的方法initializeBean中，我们可以看到为什么会这样执行。
    }

    /**
     * 通过@Bean的方式可以指定initMethod
     * @return
     */
    @Bean(initMethod = "init")
    public MyBean myBean() {
        return new MyBean();
    }
}
