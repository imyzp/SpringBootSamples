package com.yzp.spring.springbootsamples.basic.usage.validation.custom_message_api;

import java.util.Date;

public class MessageBean {
    private int code = 200;	// 返回编码
    private String message = "";	// 提示信息
    private Object data = null;		// 其他信息
    private Long time = new Date().getTime();

    public MessageBean() {
        super();
    }

    public MessageBean(String message) {
        super();
        this.message = message;
    }

    public MessageBean(Object data) {
        super();
        this.data = data;
    }

    public MessageBean(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public MessageBean(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }

    public MessageBean(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public MessageBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MessageBean setData(Object data) {
        this.data = data;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public MessageBean setTime(Long time) {
        this.time = time;
        return this;
    }
}
