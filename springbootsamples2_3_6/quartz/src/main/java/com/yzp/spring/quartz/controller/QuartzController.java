package com.yzp.spring.quartz.controller;

import com.yzp.spring.quartz.util.QuartzBean;
import com.yzp.spring.quartz.util.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 测试
 * @Author yaozhenpeng
 * @Time 2021/12/28 23:26
 */
@RestController
public class QuartzController {
    @Autowired
    private Scheduler scheduler;

    /**
     * 创建任务
     * @param quartzBean
     * @return
     */
    @RequestMapping("/createJob")
    @ResponseBody
    public String  createJob(QuartzBean quartzBean)  {
        try {
            //进行测试所以写死
            quartzBean.setJobClass("com.yzp.spring.quartz.job.Job1");
            quartzBean.setJobName("test1");
            quartzBean.setCronExpression("*/10 * * * * ?");
            QuartzUtils.createScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return "创建失败";
        }
        return "创建成功";
    }

    /**
     * 暂停任务
     * @return
     */
    @RequestMapping("/pauseJob")
    @ResponseBody
    public String  pauseJob()  {
        try {
            QuartzUtils.pauseScheduleJob (scheduler,"test1");
        } catch (Exception e) {
            return "暂停失败";
        }
        return "暂停成功";
    }

    /**
     * 运行一次
     * @return
     */
    @RequestMapping("/runOnce")
    @ResponseBody
    public String  runOnce()  {
        try {
            QuartzUtils.runOnce (scheduler,"test1");
        } catch (Exception e) {
            return "运行一次失败";
        }
        return "运行一次成功";
    }

    /**
     * 重新开始
     * @return
     */
    @RequestMapping("/resume")
    @ResponseBody
    public String  resume()  {
        try {
            QuartzUtils.resumeScheduleJob(scheduler,"test1");
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }

    /**
     *
     * @param quartzBean
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String  update(QuartzBean quartzBean)  {
        try {
            //进行测试所以写死
            quartzBean.setJobClass("com.yzp.spring.quartz.job.Job1");
            quartzBean.setJobName("test1");
            quartzBean.setCronExpression("10 * * * * ?");
            QuartzUtils.updateScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }
}
