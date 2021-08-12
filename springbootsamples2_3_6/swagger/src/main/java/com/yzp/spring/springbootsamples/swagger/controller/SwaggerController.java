package com.yzp.spring.springbootsamples.swagger.controller;

import com.yzp.spring.springbootsamples.swagger.model.SwaggerModel;
import com.yzp.spring.springbootsamples.swagger.model.SwaggerModelForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * swagger常用注解和配置 参考：https://blog.csdn.net/u014693253/article/details/81127906
 * 本类只列出一些，当有用到其它配置在添加
 */
@RestController("/test")
@Api(tags ="测试生成接口文档")
public class SwaggerController {


    @ApiOperation(value = "方法1", notes = "方法描述")
    @PostMapping("/swagger")
    public ResponseEntity<SwaggerModel> swagger(@RequestBody SwaggerModelForm s) {
        SwaggerModel sm=new SwaggerModel();
        sm.setAge(20);
        sm.setName("yzp");
        sm.setState("hard");
        return new ResponseEntity<>(sm, HttpStatus.OK);
    }
    @ApiOperation(value = "方法2", notes = "方法描述")
    @PostMapping("/swagger2")
    public ResponseEntity<SwaggerModel> swagger2(@RequestBody SwaggerModelForm s) {
        SwaggerModel sm=new SwaggerModel();
        sm.setAge(20);
        sm.setName("yzp");
        sm.setState("hard");
        return new ResponseEntity<>(sm, HttpStatus.OK);
    }
}
