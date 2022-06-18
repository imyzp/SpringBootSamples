package com.yzp.spring.springbootsamples.redisson.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/6/19 1:40
 */
@RestController
public class RedissonController {
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("lock")
    public void lock(){
        System.out.println("lockTest");
    }
}
