package com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.enums;

public enum UserRoleEnum {
    ADMIN("0","管理员");
    private String code;
    private String name;

    UserRoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UserRoleEnum setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UserRoleEnum setCode(String code) {
        this.code = code;
        return this;
    }
}
