package com.yzp.spring.springbootsamples.elasticjob.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticJobRegistryConfig {
    /**
     * zk注册中心配置类
     * @param serverList
     * @param nameSpace
     * @return
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(@Value("${job.regCenter.serverList}") String serverList,
                                                           @Value("${job.regCenter.namespace}") String nameSpace)
    {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, nameSpace);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

}
