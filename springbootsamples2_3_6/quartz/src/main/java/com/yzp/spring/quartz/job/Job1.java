package com.yzp.spring.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 定时任务类
 * @Author yaozhenpeng
 * @Time 2021/12/28 22:40
 */
public class Job1 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO 这里写定时任务的执行逻辑
        System.out.println("简单的定时任务执行时间："+new Date().toLocaleString());
    }
}
