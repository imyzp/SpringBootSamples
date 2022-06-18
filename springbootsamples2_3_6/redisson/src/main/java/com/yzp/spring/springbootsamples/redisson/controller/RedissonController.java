package com.yzp.spring.springbootsamples.redisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/6/19 1:40
 */
@RestController
public class RedissonController {
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("lock")
    public void lock(){
        // 定义一把锁
        RLock lock = redissonClient.getLock("lockId");

        try {
            //waitTime 相同的锁被别的线程先获取了就阻塞一段时间
            //leaseTime 获取到当前的锁后超过这个时间会自动释放
            lock.tryLock(30, 30, TimeUnit.SECONDS);

            // 利用此可实现场景问题：并发请求接口时传入相同的数据，利用这个锁可以让期中一个线程等待30s,先入的线程可以将数据放到缓存，然后进行其他业务处理
            // 后入的线程，我们判断缓存是否有同样数据，有就报异常即可，如果没有相同数据就也放到缓存，以便又有线程进来，这30s的时间差即可放好缓存，判断缓存是否有同样数据
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("lockTest");
    }

}
