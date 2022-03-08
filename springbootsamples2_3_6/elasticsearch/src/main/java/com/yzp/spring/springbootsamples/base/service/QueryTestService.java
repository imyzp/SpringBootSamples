package com.yzp.spring.springbootsamples.base.service;

import com.yzp.spring.springbootsamples.base.model.DocQueryRes;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * es单查询操作
 */
@Service
public class QueryTestService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    // 查询索引的所有数据
    public DocQueryRes getDocData(String indexName) throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices(indexName);

        // 设置查询请求条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询所有数据
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        // 发送请求，获取响应
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        TimeValue took = searchResponse.getTook();
        boolean timedOut = searchResponse.isTimedOut();
        DocQueryRes docQueryRes = new DocQueryRes().setHits(hits).setTook(took).setTimedOut(timedOut);
        return docQueryRes;
    }
    // 关键词精确查找文档
    public DocQueryRes termQuery(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices(indexName);

        // 设置查询请求条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 根据关键词查找
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", "20");
        searchSourceBuilder.query(termQueryBuilder);
        // 发送请求，获取响应
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        TimeValue took = searchResponse.getTook();
        boolean timedOut = searchResponse.isTimedOut();
        DocQueryRes docQueryRes = new DocQueryRes().setHits(hits).setTook(took).setTimedOut(timedOut);
        return docQueryRes;
    }
    // 分页查询
    public DocQueryRes page(String indexName, Integer pageNo, Integer pageSize) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices(indexName);

        // 设置查询请求条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询所有数据
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        // 增加分页条件
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        // 发送请求，获取响应
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        TimeValue took = searchResponse.getTook();
        boolean timedOut = searchResponse.isTimedOut();
        DocQueryRes docQueryRes = new DocQueryRes().setHits(hits).setTook(took).setTimedOut(timedOut);
        return docQueryRes;
    }
    // 排序
    public DocQueryRes sort(String indexName, String sort) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices(indexName);

        // 设置查询请求条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询所有数据
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        // 排序
        if(sort.equals("asc")){
            searchSourceBuilder.sort("age", SortOrder.ASC);
        }else{
            searchSourceBuilder.sort("age", SortOrder.DESC);
        }

        // 发送请求，获取响应
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        TimeValue took = searchResponse.getTook();
        boolean timedOut = searchResponse.isTimedOut();
        DocQueryRes docQueryRes = new DocQueryRes().setHits(hits).setTook(took).setTimedOut(timedOut);
        return docQueryRes;
    }
    // 过滤字段
    public Object filterColumn(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices(indexName);

        // 设置查询请求条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询所有数据
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        // 设置过滤字段
        String[] excludes = {};
        String[] includes = {"name","age"};
        searchSourceBuilder.fetchSource(includes,excludes);
        // 发送请求，获取响应
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        TimeValue took = searchResponse.getTook();
        boolean timedOut = searchResponse.isTimedOut();
        DocQueryRes docQueryRes = new DocQueryRes().setHits(hits).setTook(took).setTimedOut(timedOut);
        return docQueryRes;
    }
}
