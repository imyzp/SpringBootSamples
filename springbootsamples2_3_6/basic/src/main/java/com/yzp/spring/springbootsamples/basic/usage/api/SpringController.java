package com.yzp.spring.springbootsamples.basic.usage.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SpringController {

    /**
     * 使用 spring的返回编码 ResponseStatus
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/springApi")
    public User test2(){
        User yao = new User().setLogin_Date(new Date()).setLogin_name("yao");
        return yao;
    }
    @GetMapping("/springApi2")
    public ResponseEntity<?> test3(){
        User yao = new User().setLogin_Date(new Date()).setLogin_name("yao");
        return ResponseEntity.ok(yao);
    }

    /**
     * 通过路径传参
     * @param name
     * @return
     */
    @GetMapping("/param/{name}")
    public ResponseEntity<?> test4(@PathVariable("name") String name){
        return ResponseEntity.ok(name);
    }
}
