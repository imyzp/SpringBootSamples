package com.yzp.spring.springbootsamples.basic.usage.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 监听事件测试
 * @Author yaozhenpeng
 * @Time 2021/11/24 0:03
 */
@RestController
public class EventController {
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/event/test")
    public ResponseEntity<?> eventTest(){
        EventModel eventModel = new EventModel();
        eventModel.setName("事件1");
        eventModel.setPass("1111");
        // 触发事件异步执行
        eventPublisher.publishEvent(new EventHappen_1(eventModel));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
