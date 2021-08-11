package com.yzp.spring.springbootsamples.redis.model;

import java.io.Serializable;

public class Users implements Serializable {
    private static final long serialVersionUID = -802851606656677239L;
    private Integer id;

    private String loginName;//登录名

    private String passWord;//密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
