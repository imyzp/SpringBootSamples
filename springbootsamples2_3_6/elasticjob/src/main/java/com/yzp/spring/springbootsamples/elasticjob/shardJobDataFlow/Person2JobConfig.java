package com.yzp.spring.springbootsamples.elasticjob.shardJobDataFlow;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.yzp.spring.springbootsamples.elasticjob.shardJob.PersonJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Person2JobConfig {
    @Value("${job.personDataFlowJob.cron}")
    private String cron;
    @Value("${job.personDataFlowJob.shardingTotalCount}")
    private Integer shardingTotalCount;
    @Value("${job.personDataFlowJob.shardingParameter}")
    private String shardingParameter;
    @Value("${job.personDataFlowJob.jobName}")
    private String jobName;
    @Value("${job.personDataFlowJob.className}")
    private String className;
    @Autowired
    private DataSource dataSource;

    @Bean(initMethod = "init")
    public JobScheduler helloJobScheduler(Person2Job personJob, ZookeeperRegistryCenter zookeeperRegistryCenter)
    {
        // 配置一个事件监听，记录任务日志，JobEventConfiguration是一个接口，可以自定义实现逻辑，elasticjob默认给了一个实现 JobEventRdbConfiguration ,注入DataSource使用
        // 增加此配置后，启动项目会生成两张表JOB_EXECUTION_LOG:记录每次作业的执行历史、JOB_STATUS_TRACE_LOG:记录任务状态变更痕迹表。可通过每次作业运行的taskid查询作业状态变化
        // 的生命周期和运行轨迹
        JobEventRdbConfiguration jobEventRdbConfiguration = new JobEventRdbConfiguration(dataSource);

        return new SpringJobScheduler(personJob,zookeeperRegistryCenter,
                getLiteJobConfiguration(className,jobName,cron,shardingTotalCount),jobEventRdbConfiguration);
    }
    private LiteJobConfiguration getLiteJobConfiguration(String jobClassName, String jobName, String cron, Integer shardingTotalCount)
    {

        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
                .shardingItemParameters(shardingParameter) // 分片参数，前面是分片序号，后面是分片数据 此处0代表女 1代表男
                .build();
        DataflowJobConfiguration dataflowJobConfiguration = new DataflowJobConfiguration(jobCoreConfiguration,jobClassName,true);
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(dataflowJobConfiguration).overwrite(true).build();
        return liteJobConfiguration;
    }
}
