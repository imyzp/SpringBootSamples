package com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.api;

import com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.annotation.PermissionCheck;
import com.yzp.spring.springbootsamples.basic.usage.aop.exp.permissioncheck.enums.UserRoleEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PermissionCheck(roles = UserRoleEnum.ADMIN)
@RestController("")
public class PermissionController {
    @GetMapping("/per/test")
    public void test(){
        System.out.println("sdf");
    }
}
