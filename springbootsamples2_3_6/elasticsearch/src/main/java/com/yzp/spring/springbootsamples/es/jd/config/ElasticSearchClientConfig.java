package com.yzp.spring.springbootsamples.es.jd.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

/**
 * todo 可以将 RestHighLevelClient 注入spring 中
 */
@Configuration
public class ElasticSearchClientConfig {
    public RestHighLevelClient rest(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1",9200,"http")
                )
        );
        return client;
    }
}
