package com.yzp.spring.springbootsamples.basic.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 发生事件1
 * @Author yaozhenpeng
 * @Time 2021/11/23 23:57
 */
public class EventHappen_1 extends ApplicationEvent {

    public EventHappen_1(Object source) {
        super(source);
    }
}
