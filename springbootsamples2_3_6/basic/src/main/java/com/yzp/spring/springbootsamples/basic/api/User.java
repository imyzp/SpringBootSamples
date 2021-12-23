package com.yzp.spring.springbootsamples.basic.api;

import java.util.Date;

public class User {
    /**
     * 自动转换成驼峰到前端
     */
    private String login_name;
    private Date login_Date;

    public String getLogin_name() {
        return login_name;
    }

    public User setLogin_name(String login_name) {
        this.login_name = login_name;
        return this;
    }

    public Date getLogin_Date() {
        return login_Date;
    }

    public User setLogin_Date(Date login_Date) {
        this.login_Date = login_Date;
        return this;
    }
}
