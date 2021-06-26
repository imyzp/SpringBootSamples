package com.yzp.spring.springbootsamples.basic.task.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    private static int count=1;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void dataCount(){
        System.out.println("数据统计第-----"+count+++"-----次");
    }
}
