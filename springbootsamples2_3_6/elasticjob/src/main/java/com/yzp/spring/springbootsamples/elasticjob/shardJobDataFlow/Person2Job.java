package com.yzp.spring.springbootsamples.elasticjob.shardJobDataFlow;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Person2Job implements DataflowJob<Person2> {
    private static final Logger logger = LoggerFactory.getLogger(Person2Job.class);
    @Autowired
    private Person2Service person2Service;
    @Value("${server.port}")
    private Integer port;

    /**
     * 数据拉取
     * 定时任务一旦触发fetchData将不停的拉取数据，直到fetchData返回null,当前任务才停止
     * @param shardingContext
     * @return
     */
    @Override
    public List<Person2> fetchData(ShardingContext shardingContext) {
        Integer sex = Integer.valueOf(shardingContext.getShardingParameter());
        List<Person2> person2List = person2Service.findPersonBySex(sex,5);
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("抓取时间：{}，服务端口为：port:{},性别：{},数据抓取：person2List:{}",now,port,sex==0?"女":"男",person2List);
        return person2List;
    }

    /**
     * 数据处理
     * @param shardingContext
     * @param data
     */
    @Override
    public void processData(ShardingContext shardingContext, List<Person2> data) {
        logger.info("服务端口为：port:{},处理数据：data:{}",port,data);
        person2Service.doSomething(data);
    }
}
