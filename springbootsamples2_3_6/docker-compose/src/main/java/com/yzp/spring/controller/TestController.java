package com.yzp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String sendMail()
    {
        Long views = redisTemplate.opsForValue().increment("views");
        return "hello ,yzp, views:"+views;
    }
}
