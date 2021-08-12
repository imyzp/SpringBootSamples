package com.yzp.spring.springbootsamples.basic.async.impl;

import com.yzp.spring.springbootsamples.basic.async.IAscyService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 异步任务处理
 */
@Service
public class AscyService implements IAscyService {

    @Override
    public void batchAdd() {
        try {
            Thread.sleep(5 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("异步任务进行中"+new Date().getTime());
    }
}
