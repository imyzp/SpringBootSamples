package com.yzp.spring.springbootsamples.basic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @SpringBootApplication用于标识一个主程序类，说明当前是Spring Boot项目
 * @EnableAsync 开启异步任务注解，要异步执行的方法上加注解@Async即可，参考 asyncModule
 * @EnableScheduling 开启定时任务注解，参考  scheduleModule
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class BasicApplication {
    public static void main(String[] args){
        SpringApplication.run(BasicApplication.class,args);
    }

}
