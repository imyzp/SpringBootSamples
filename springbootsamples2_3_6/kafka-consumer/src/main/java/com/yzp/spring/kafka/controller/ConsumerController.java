package com.yzp.spring.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 消费者监听topic=testTopic的消息
 * @Author yaozhenpeng
 * @Time 2022/5/5 23:04
 */
@Component
public class ConsumerController {
    /**
     * 生产者发送消息后，消费者项目后台日志，消费新消息成功，并且输出topic，数据所在kafka的分区编号，新消息内容。
     * @param record
     */
    @KafkaListener(topics = "wtopic04")
    public void onMessage(ConsumerRecord<?, ?> record){
        //insertIntoDb(buffer);//这里为插入数据库代码
        //System.out.println("message: " + message);
        System.out.println("简单消费，record："+record.topic()+"-"+record.partition()+"-"+record.value());

    }
}
