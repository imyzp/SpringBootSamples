package com.yzp.spring.springbootsamples.basic.source.beanpostprocessor.base;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/4/28 23:40
 */
public class MyBean {
    private String beanName;
    private String className;
    public MyBean() {
        System.out.println("MyBean constructor");
    }
    public void init() {
        System.out.println("MyBean is init");
    }

    public String getBeanName() {
        return beanName;
    }

    public MyBean setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public MyBean setClassName(String className) {
        this.className = className;
        return this;
    }
}
