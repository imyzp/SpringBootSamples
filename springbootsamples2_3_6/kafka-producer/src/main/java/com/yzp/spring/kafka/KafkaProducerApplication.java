package com.yzp.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/5/5 22:43
 */
@SpringBootApplication
@ComponentScan("com.yzp.spring.kafka.config")
@ComponentScan("com.yzp.spring.kafka.controller")
public class KafkaProducerApplication {
    public static void main(String[] args){
        SpringApplication.run(KafkaProducerApplication.class,args);
    }
}
