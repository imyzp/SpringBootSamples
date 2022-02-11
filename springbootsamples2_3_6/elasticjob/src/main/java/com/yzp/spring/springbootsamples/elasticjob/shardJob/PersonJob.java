package com.yzp.spring.springbootsamples.elasticjob.shardJob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PersonJob implements SimpleJob {
    private static final Logger logger = LoggerFactory.getLogger(PersonJob.class);

    @Autowired
    private PersonService personService;
    @Value("${server.port}")
    private Integer port;

    @Override
    public void execute(ShardingContext shardingContext) {
        String taskId = shardingContext.getTaskId();
        String shardingParameter = shardingContext.getShardingParameter();
        logger.info("端口：{}，定时任务开始：taskId:{},shardingParameter:{}",port,taskId,shardingParameter);
        personService.doSomething(Integer.valueOf(shardingParameter));
    }
}
