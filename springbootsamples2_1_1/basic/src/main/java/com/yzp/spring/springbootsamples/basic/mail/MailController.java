package com.yzp.spring.springbootsamples.basic.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  步骤：1、导入jar包    参见pom中  邮件相关
 *        2、 在application.yml中添加spring.mail相关属性，参见注释
 *        3、 然后就可以用发送邮件
 */
@RestController
public class MailController {

    @Autowired
    JavaMailSender javaMailSender;


    @GetMapping("sendMail")
    public String sendMail()
    {
        //封装简单的邮件内容
        SimpleMailMessage message=new SimpleMailMessage();
        //邮件主题
        message.setSubject("姚振鹏springboot邮件发送测试");
        message.setText("好好学习，天天向上");

        //发件人
        message.setFrom("601405772@qq.com");
        message.setTo("18065220862@163.com");

        javaMailSender.send(message);
        return "success";
    }
}
