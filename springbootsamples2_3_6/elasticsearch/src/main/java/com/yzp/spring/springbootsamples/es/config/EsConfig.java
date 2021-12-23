//package com.yzp.spring.springbootsamples.es.config;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Configuration;
//
///**
// *  将 RestHighLevelClient 注入spring 中
// */
//@Configuration
//public class EsConfig {
//    public RestHighLevelClient rest(){
//        System.out.println("adf");
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("127.0.0.1",9200,"http")
//                )
//        );
//        return client;
//    }
//}
