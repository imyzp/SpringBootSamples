package com.yzp.spring.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 生产者业务逻辑
 * @Author yaozhenpeng
 * @Time 2022/5/5 22:54
 */
@RestController("/api/kafka/")
public class ProducterController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    /**
     * 在浏览器中调用http://localhost:8080/api/kafka/send?message=wt02此地址推送生产者消息。
     * 可以登录到Kafka后台，使用消费者命令查看消息是否发布成功，
     * @param message
     * @return
     */
    @GetMapping("send")
    @ResponseBody
    public boolean send(@RequestParam String message) {
        try {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("wtopic04", message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.err.println("wtopic04 - 生产者 发送消息失败：" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                    System.out.println("wtopic04 - 生产者 发送消息成功：" + stringObjectSendResult.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("test")
    @ResponseBody
    public String test(){
        System.out.println("hello world!");
        return "ok";
    }
}
