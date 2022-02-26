package com.yzp.spring.springbootsamples.base.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * es查询操作
 */
@Service
public class QueryTestService {
    // 常见查询
    public void query(){
        // 单字段范围查找 >= gte > gt  , <= lte  < lt
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gte(10).lte(30);
        RangeQueryBuilder rangeQueryBuilder2 = QueryBuilders.rangeQuery("age").gt(10).lt(30);
        // 关键字精确查询，不对查询条件进行分词
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "yaozhenpeng");

    }

    // 8 组合查询
    // `bool`把各种其它查询通过`must`（必须 ）、`must_not`（必须不）、`should`（应该）的方式进行组合
    public void boolQuery(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 必须
        boolQueryBuilder.must();
        // 必须不
        boolQueryBuilder.mustNot();
        // 应该
        boolQueryBuilder.should();

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
