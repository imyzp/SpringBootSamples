//package com.yzp.spring.springbootsamples.elasticjob.demojob;
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
//public class DemoJobConfig {
//    @Value("${job.demojob.cron}")
//    private String cron;
//    @Value("${job.demojob.shardingTotalCount}")
//    private Integer shardingTotalCount;
//    @Value("${job.demojob.jobName}")
//    private String jobName;
//    @Value("${job.demojob.className}")
//    private String className;
//
//    @Bean
//    public DemoJob demoJob(){
//        return new DemoJob();
//    }
//
//    @Bean(initMethod = "init")
//    public JobScheduler demoJobScheduler(DemoJob demoJob, ZookeeperRegistryCenter zookeeperRegistryCenter){
//        return new SpringJobScheduler(demoJob,
//                zookeeperRegistryCenter,
//                getLiteJobConfiguration(className,jobName,cron,shardingTotalCount));
//    }
//
//    private LiteJobConfiguration getLiteJobConfiguration(String jobClassName, String jobName, String cron, Integer shardingTotalCount){
//        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobName,cron,shardingTotalCount).build();
//        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClassName);
//        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
//        return liteJobConfiguration;
//    }
//}
