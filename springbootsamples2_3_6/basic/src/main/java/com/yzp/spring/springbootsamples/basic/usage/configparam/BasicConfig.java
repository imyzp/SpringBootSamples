package com.yzp.spring.springbootsamples.basic.usage.configparam;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 获取系统配置文件参数
 * @Author yaozhenpeng
 * @Time 2022/1/21 20:44
 */
@Configuration
@ConfigurationProperties(prefix = "basic.config")
public class BasicConfig {
    private String devname;

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }
}
