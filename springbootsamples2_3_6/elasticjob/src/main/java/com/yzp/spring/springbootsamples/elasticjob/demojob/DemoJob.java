package com.yzp.spring.springbootsamples.elasticjob.demojob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class DemoJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("demo task job .............................");
        System.out.println("作业名称："+shardingContext.getJobName());
        System.out.println("作业任务ID:"+shardingContext.getTaskId());
        System.out.println("分片总数："+shardingContext.getShardingTotalCount());
        System.out.println("作业自定义参数："+shardingContext.getJobParameter());
        System.out.println("分片项："+shardingContext.getShardingItem());
        System.out.println("作业分片参数："+shardingContext.getShardingParameter());
    }
}
