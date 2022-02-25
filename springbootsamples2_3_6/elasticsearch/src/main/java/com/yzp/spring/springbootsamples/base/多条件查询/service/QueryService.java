package com.yzp.spring.springbootsamples.base.多条件查询.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    /**
     * match 匹配类型查询，会把查询条件进行分词，然后进行查询，多个词条之间是 or 的关系在 Postman 中，向ES 服务器发 GET 请求 ：http://127.0.0.1:9200/student/_search
     * }
     * }
     */
    public void test1(){

    }


    public void test() {
        // must 不会对搜索词进行分词处理，而是作为一个整体与目标字段进行匹配，若完全匹配，则可查询到
        // must not
        // should  or

        BoolQueryBuilder queryBuilder= QueryBuilders
                .boolQuery()// 构建多条件查询
                .must(QueryBuilders.rangeQuery("age").gte(1).lt(50))// 范围查询
                .must(QueryBuilders.termQuery("name","yao"))// 精确匹配
                .must(QueryBuilders.termsQuery("desc","你","在"))// 一次匹配多个值
        ;
        //
        ;
    }
}
