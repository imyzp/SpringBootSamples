package com.yzp.spring.springbootsamples.swagger.config.swagger2;

import io.swagger.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Configuration  表明是个配置文件，启动时会加载
 * @EnableSwagger2  使用此注解来启用swagger
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 创建接口文档
     * @return
     */
    @Bean
    public Docket createRestApi()
    {
        List<Parameter> parameterList=new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//创建接口文档的基本信息
                .select()//控制哪些接口暴露给swagger展示（如何控制在研究)
                //指定包basePackage下的接口用于展示
                .apis(RequestHandlerSelectors.basePackage("com.yzp.spring.springbootsamples.swagger.controller"))
                .paths(PathSelectors.any())//在研究
                .build();//调用创建方法
    }

    /**
     * 创建接口文档的基本信息
     * @return
     */
    private ApiInfo apiInfo()
    {
        //name创建人、url项目地址、email邮箱地址
        Contact contact=new Contact("姚振鹏","","邮箱地址");
        return new ApiInfoBuilder()
                .title("RESTful API")//文档名称
                .description("RESTful API")//文档描述
                .contact(contact)
                .version("1.0")//版本
                .build();//调用创建方法
    }
}
