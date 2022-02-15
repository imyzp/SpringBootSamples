//package com.yzp.spring.springbootsamples.elasticjob.shardJob;
//
//import com.dangdang.ddframe.job.config.JobCoreConfiguration;
//import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
//import com.dangdang.ddframe.job.lite.api.JobScheduler;
//import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
//import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PersonJobConfig {
//    @Value("${job.personJob.cron}")
//    private String cron;
//    @Value("${job.personJob.shardingTotalCount}")
//    private Integer shardingTotalCount;
//    @Value("${job.personJob.shardingParameter}")
//    private String shardingParameter;
//    @Value("${job.personJob.jobName}")
//    private String jobName;
//    @Value("${job.personJob.className}")
//    private String className;
//
//    @Bean(initMethod = "init")
//    public JobScheduler helloJobScheduler(PersonJob personJob, ZookeeperRegistryCenter zookeeperRegistryCenter)
//    {
//        return new SpringJobScheduler(personJob,zookeeperRegistryCenter,
//                getLiteJobConfiguration(className,jobName,cron,shardingTotalCount));
//    }
//
//    private LiteJobConfiguration getLiteJobConfiguration(String jobClassName, String jobName, String cron, Integer shardingTotalCount)
//    {
//
//        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
//                .shardingItemParameters(shardingParameter) // 分片参数，前面是分片序号，后面是分片数据
//
//                .build();
//
//        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClassName);
//        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration)
//                // 配置分片策略
//                // com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy
//                // com.dangdang.ddframe.job.lite.api.strategy.impl.RotateServerByNameJobShardingStrategy
//                .jobShardingStrategyClass("com.dangdang.ddframe.job.lite.api.strategy.impl.OdevitySortByNameJobShardingStrategy")
//                .overwrite(true).build();
//        return liteJobConfiguration;
//    }
//}
