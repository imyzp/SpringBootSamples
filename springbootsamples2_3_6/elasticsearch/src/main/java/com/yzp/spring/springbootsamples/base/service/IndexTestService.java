package com.yzp.spring.springbootsamples.base.service;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description es索引操作
 * @Author yaozhenpeng
 * @Time 2022/2/26 18:57
 */
@Service
public class IndexTestService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    // 创建索引
    public boolean createIndex(String indexName) throws IOException {
        // 获取当前时间戳
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().atZone(zoneId).toInstant();
        long l = instant.toEpochMilli();

        indexName = indexName+"_"+l;
        // 创建索引-请求对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        // 发送请求，获取响应
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        // 得到操作状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        return acknowledged;
    }
}
