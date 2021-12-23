package com.yzp.spring.springbootsamples.basic.event;

import org.springframework.scheduling.annotation.Async;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 事件监听器
 * @Author yaozhenpeng
 * @Time 2021/11/23 23:55
 */
public class EventListener {


    @Async("asyncExecutor")
    @org.springframework.context.event.EventListener(classes = EventHappen_1.class)
    public void listenEventHappen_1(EventHappen_1 eventHappen_1)
    {
        EventModel source = (EventModel) eventHappen_1.getSource();

        System.out.println("触发EventHappen_1事件"+source.getName()+"_"+source.getPass());
    }

}
