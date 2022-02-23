package com.yzp.spring.springbootsamples.basic.aop.exp.permissioncheck.api;

import com.yzp.spring.springbootsamples.basic.aop.exp.permissioncheck.annotation.PermissionCheck;
import com.yzp.spring.springbootsamples.basic.aop.exp.permissioncheck.enums.UserRoleEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
public class PermissionController2 {
    @PermissionCheck(roles = UserRoleEnum.ADMIN)
    @GetMapping("/per/test/method")
    public void test(){
        System.out.println("sdfdsf");
    }
}
