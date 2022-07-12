package com.yzp.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 通过docker部署应用
 * @Author yaozhenpeng
 * @Time 2022/7/13 0:06
 */
@SpringBootApplication
public class DockerApplication {
    public static void main(String[] args){
        SpringApplication.run(DockerApplication.class,args);
    }
}
