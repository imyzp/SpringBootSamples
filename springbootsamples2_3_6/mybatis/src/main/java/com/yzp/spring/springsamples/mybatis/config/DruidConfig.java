package com.yzp.spring.springsamples.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置：
 *   1、参照application.yml  中spring.datasource
 *   2、本类的配置
 *   3、pom  数据库访问相关
 * 具体作用参照其中注解
 * druid控制台连接：http://localhost:8089/sb/druid/index.html
 */

//@Configuration表明是个配置文件，启动时会加载
@Configuration
public class DruidConfig {

    //绑定数据源配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid监控
     * 1.配置一个管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //配置初始化参数
        Map<String,String> initParam=new HashMap<>();
        //访问的用户名密码
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD,"123");
        //允许访问的ip,默认所有ip访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW,"");
        //禁止访问的ip（例子）
        initParam.put(StatViewServlet.PARAM_NAME_DENY,"192.168.10.1");
        bean.setInitParameters(initParam);
        return bean;
    }
    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean filter(){
        FilterRegistrationBean<Filter> bean=new FilterRegistrationBean<Filter>();
        bean.setFilter(new WebStatFilter());

        //配置初始化参数
        Map<String,String> initParam=new HashMap<>();
        //排除请求
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        //拦截所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
