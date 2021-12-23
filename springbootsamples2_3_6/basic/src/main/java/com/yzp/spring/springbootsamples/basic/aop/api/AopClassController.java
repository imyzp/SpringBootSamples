package com.yzp.spring.springbootsamples.basic.aop.api;

import com.yzp.spring.springbootsamples.basic.aop.model.UserAop;
import com.yzp.spring.springbootsamples.basic.aop.annotation.CustomClassAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CustomClassAnnotation(value = 0,name = "aopClassTest",description = "执行aopClassTest方法")
@RestController
@RequestMapping("")
public class AopClassController {

    @GetMapping("/aoptest/class/{id}")
    public UserAop aopTest(@PathVariable("id") Integer id){
        return new UserAop().setName("dd").setId(id);
    }
}