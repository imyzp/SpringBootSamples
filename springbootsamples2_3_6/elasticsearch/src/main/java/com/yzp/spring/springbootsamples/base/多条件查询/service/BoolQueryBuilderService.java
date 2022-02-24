package com.yzp.spring.springbootsamples.base.多条件查询.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

@Service
public class BoolQueryBuilderService {

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
