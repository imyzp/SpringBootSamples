package com.yzp.spring.springbootsamples.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/6/19 1:39
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        SingleServerConfig singleServerConfig = config.useSingleServer();
        //可以用"rediss://"来启用SSL连接
        singleServerConfig.setAddress("redis://127.0.0.1:6379");
        singleServerConfig.setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
