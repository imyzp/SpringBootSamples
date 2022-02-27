package com.yzp.spring.springbootsamples.base.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzp.spring.springbootsamples.base.model.BatchDocWriteRes;
import com.yzp.spring.springbootsamples.base.model.DocDetailVO;
import com.yzp.spring.springbootsamples.base.model.DocGetRes;
import com.yzp.spring.springbootsamples.base.model.DocWriteRes;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 往索引中操作文档
 * @Author yaozhenpeng
 * @Time 2022/2/26 21:09
 */
@Service
public class DocTestService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 往指定索引中添加文档
    public DocWriteRes addDoc2Index(String indexName) throws IOException {
        IndexRequest indexRequest = new IndexRequest();
        // 指定索引
        indexRequest.index(indexName);
        // 指定当前添加的文档唯一id
        indexRequest.id("1001");
        // 生成文档数据为json
        DocDetailVO docDetail = new DocDetailVO();
        docDetail.setName("zhangsan");
        docDetail.setAge(30);
        docDetail.setSex("男");
        ObjectMapper objectMapper = new ObjectMapper();
        String docJson = objectMapper.writeValueAsString(docDetail);
        indexRequest.source(docJson, XContentType.JSON);
        // 发送请求，获取响应
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        String index = indexResponse.getIndex();
        String id = indexResponse.getId();
        DocWriteResponse.Result result = indexResponse.getResult();
        DocWriteRes indexDocDetailRes = new DocWriteRes().setIndex(index).setId(id).setResult(result);
        return indexDocDetailRes;
    }
    // 批量忘指定索引中添加文档
    public BatchDocWriteRes batchAddDoc2Index(String indexName) throws IOException {
        // 设置批量请求
        BulkRequest bulkRequest = new BulkRequest();
        for (int i=0;i<100;i++){
            IndexRequest indexRequest = new IndexRequest();
            // 指定索引
            indexRequest.index(indexName);
            // 指定当前添加的文档唯一id
            indexRequest.id("100"+i);
            // 指定文档数据
            indexRequest.source(XContentType.JSON, "name","zhangsan"+i);
            bulkRequest.add(indexRequest);
        }
        // 发送请求获取响应
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        TimeValue took = bulkResponse.getTook();
        BulkItemResponse[] items = bulkResponse.getItems();
        BatchDocWriteRes batchDocWriteRes = new BatchDocWriteRes().setTook(took).setItems(items);
        return batchDocWriteRes;
    }
    // 更新索引文档内容
    public DocWriteRes updateDocByIndex(String indexName) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        // 指定索引
        updateRequest.index(indexName);
        // 指定当前更新的文档唯一id
        updateRequest.id("1001");
        // 指定文档更新的内容
        updateRequest.doc(XContentType.JSON,"sex","男");
        // 发送请求，获取响应
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        DocWriteRes indexDocDetailRes = new DocWriteRes().setIndex(updateResponse.getIndex())
                .setId(updateResponse.getId())
                .setResult(updateResponse.getResult());
        return indexDocDetailRes;
    }
    // 查询文档
    public DocGetRes getDoc(String indexName) throws IOException {
        GetRequest getRequest = new GetRequest();
        // 指定索引
        getRequest.index(indexName);
        // 指定当前更新的文档唯一id
        getRequest.id("1001");

        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        String index = getResponse.getIndex();
        String type = getResponse.getType();
        String id = getResponse.getId();
        String sourceAsString = getResponse.getSourceAsString();
        DocGetRes docGetRes = new DocGetRes().setId(id).setIndex(index).setType(type).setSourceAsString(sourceAsString);
        return docGetRes;
    }
    // 批量删除文档
    public BatchDocWriteRes batchDeleteDoc(String indexName) throws IOException {
        // 设置批量请求
        BulkRequest bulkRequest = new BulkRequest();
        for (int i=0;i<100;i++) {
            DeleteRequest deleteRequest = new DeleteRequest();
            // 指定索引
            deleteRequest.index(indexName);
            // 指定当前添加的文档唯一id
            deleteRequest.id("100"+i);
            bulkRequest.add(deleteRequest);
        }
        // 发送请求，获取响应
        BulkResponse deleteResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        TimeValue took = deleteResponse.getTook();
        BulkItemResponse[] items = deleteResponse.getItems();
        BatchDocWriteRes batchDocWriteRes = new BatchDocWriteRes().setTook(took).setItems(items);
        return batchDocWriteRes;
    }
}
