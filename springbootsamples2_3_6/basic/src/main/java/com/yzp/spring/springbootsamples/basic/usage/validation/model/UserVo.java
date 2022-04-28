package com.yzp.spring.springbootsamples.basic.usage.validation.model;

import com.yzp.spring.springbootsamples.basic.usage.validation.custom_message_api.ToolInterface;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserVo {
    @Min(groups = {ToolInterface.update.class}, value = 1, message = "ID不能小于1")
    private int id;

    @NotBlank(groups = {ToolInterface.update.class, ToolInterface.insert.class}, message = "用户名不能为空")
    private String name;

    @NotBlank(groups = {ToolInterface.update.class, ToolInterface.insert.class}, message = "密码不能为空")
    @Size(groups = {ToolInterface.update.class, ToolInterface.insert.class}, min = 6, max = 12, message = "密码长度不能小于6,大于12")
    private String password;

    @Min(value = 1, message = "年龄必须大于1岁")
    @Max(value = 200, message = "年龄必须小于200岁")
    private int age;
    private String remark;


    public int getId() {
        return id;
    }

    public UserVo setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserVo setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserVo setAge(int age) {
        this.age = age;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public UserVo setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
