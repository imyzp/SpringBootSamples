package com.yzp.spring.springbootsamples.base.service;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        // 创建索引-请求对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        // 发送请求，获取响应
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        // 得到操作状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        return acknowledged;
    }
    // todo根据索引名称查看索引详情
    public Object indexDetail(String indexName) throws IOException {

        return null;
    }

    // 删除索引
    public boolean deleteIndex(String indexName) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }
}
