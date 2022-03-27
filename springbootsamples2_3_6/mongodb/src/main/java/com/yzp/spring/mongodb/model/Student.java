package com.yzp.spring.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/3/27 22:19
 */
@Document("t_student")//
public class Student {
    @Id
    private String id;// 自增长
}
