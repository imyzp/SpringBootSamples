package com.yzp.spring.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description TODO
 * @Author yaozhenpeng
 * @Time 2022/3/27 22:50
 */
public class MongoTest extends Test {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoTest(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    // 1.创建集合
    @org.junit.jupiter.api.Test
    public void testCreateCollection(){
        boolean exists = mongoTemplate.collectionExists("products");
        if(!exists)
        {
            mongoTemplate.createCollection("products");
        }
    }
    // 2.删除集合操作
    public void testDropCollection(){
        mongoTemplate.dropCollection("products");
    }
}
