package com.yzp.spring.springbootsamples.basic.aop;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class AopController {

    @CustomAnnotation
    @GetMapping("/aoptest")
    public void aopTest(){
        System.out.println("执行方法体");
    }

}
