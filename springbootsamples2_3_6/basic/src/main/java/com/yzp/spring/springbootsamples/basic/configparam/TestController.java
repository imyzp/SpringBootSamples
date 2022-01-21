package com.yzp.spring.springbootsamples.basic.configparam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 获取系统配置测试
 * @Author yaozhenpeng
 * @Time 2022/1/21 20:49
 */
@RestController
public class TestController {
    @Autowired
    BasicConfig basicConfig;

    @GetMapping("/getBasicConfig")
    public String get(){
        return basicConfig.getDevname();
    }
}
