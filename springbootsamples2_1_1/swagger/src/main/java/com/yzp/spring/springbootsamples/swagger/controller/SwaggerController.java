package com.yzp.spring.springbootsamples.swagger.controller;

import com.yzp.spring.springbootsamples.swagger.model.SwaggerModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * swagger常用注解和配置 参考：https://blog.csdn.net/u014693253/article/details/81127906
 * 本类只列出一些，当有用到其它配置在添加
 */
@Controller
@Api(description = "用于描述这个接口是做什么用的")
public class SwaggerController {


    @PostMapping("/swagger")
    @ResponseBody
    //httpMethod请求方式
    @ApiOperation(value = "方法名", notes = "方法描述")
    public SwaggerModel swagger() {
        SwaggerModel sm=new SwaggerModel();
        sm.setAge(20);
        sm.setName("yzp");
        sm.setState("hard");
        return sm;
    }

}
