package com.yzp.spring.springsamples.activity.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class datasource {

    /**
     * 配置数据库
     * @return
     */
    @Bean
    public DataSource dataSource()
    {
        //根据自己的数据库配置
        DataSource dataSource = DataSourceBuilder.create()
                .url("jdbc:mysql://127.0.0.1:3306/fsactiviti?characterEncoding=UTF-8&nullCatalogMeansCurrent=true&serverTimezone=GMT%2B8&useSSL=false")
                .username("root")
                .password("123456")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();

        return dataSource;
    }
}
