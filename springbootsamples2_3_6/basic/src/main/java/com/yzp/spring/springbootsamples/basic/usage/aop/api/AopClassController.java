package com.yzp.spring.springbootsamples.basic.usage.aop.api;

import com.yzp.spring.springbootsamples.basic.usage.aop.model.UserAop;
import com.yzp.spring.springbootsamples.basic.usage.aop.annotation.CustomClassAnnotation;
import org.springframework.web.bind.annotation.*;

/**
 * 作用在类上的aop
 */
@CustomClassAnnotation(value = 0,name = "aopClassTest",description = "执行aopClassTest方法")
@RestController
@RequestMapping("")
public class AopClassController {

    @PostMapping("/aoptest/class/{id}")
    public UserAop aopTest(@PathVariable("id") Integer id, @RequestParam(value = "applicationName",required = false) String applicationName,@RequestBody UserAop userAop){
        return new UserAop().setName("dd").setId(id);
    }
    @GetMapping("/aoptest/class/exclude")
    public UserAop exclude(){
        return new UserAop().setName("排除不拦截");
    }
}
