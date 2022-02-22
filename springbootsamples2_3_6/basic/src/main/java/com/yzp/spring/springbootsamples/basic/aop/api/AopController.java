package com.yzp.spring.springbootsamples.basic.aop.api;

import com.yzp.spring.springbootsamples.basic.aop.model.UserAop;
import com.yzp.spring.springbootsamples.basic.aop.annotation.CustomAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作用在方法上的aop
 */
@RestController
@RequestMapping("")
public class AopController {

    @CustomAnnotation(value = 0,name = "aopTest",description = "执行aopTest方法")
    @GetMapping("/aoptest/{id}")
    public UserAop aopTest(@PathVariable("id") Integer id){
        return new UserAop().setName("dd").setId(id);
    }

}
