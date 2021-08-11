package com.yzp.spring.springbootsamples.basic.controller;


import com.yzp.spring.springbootsamples.basic.task.async.IAscyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AsyncController {

    @Autowired
    private IAscyService ascyService;

    @GetMapping("testAsync")
    public String addUsers() {
        try {
            ascyService.batchAdd();
            for(int i=0;i<100;i++)
            {
                System.out.println("同步任务执行------"+new Date().getTime());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "测试异步任务";
    }
}
