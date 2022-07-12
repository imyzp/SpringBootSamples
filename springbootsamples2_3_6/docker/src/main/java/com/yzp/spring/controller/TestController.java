package com.yzp.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/7/13 0:08
 */
@RestController
public class TestController {
    @GetMapping("test")
    public String sendMail()
    {
        return "hello ,docker";
    }
}
