package com.yzp.spring.springbootsamples.basic.usage.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ScheduleTask {
    private static int count=1;

    @PostConstruct // 启动立即运行一次
    @Scheduled(cron = "0/60 * * * * ? ")
    public void dataCount(){
        System.out.println("数据统计第-----"+count+++"-----次");
    }
}
