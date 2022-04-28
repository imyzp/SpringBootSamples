package com.yzp.spring.springbootsamples.basic.usage.aop.model;

public class UserAop {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public UserAop setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAop setName(String name) {
        this.name = name;
        return this;
    }
}
