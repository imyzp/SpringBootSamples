package com.yzp.spring.springbootsamples.elasticjob.shardJob;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private int sex;
    private int age;
    private int state;

    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public Person setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public int getState() {
        return state;
    }

    public Person setState(int state) {
        this.state = state;
        return this;
    }
}
