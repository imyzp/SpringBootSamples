package com.yzp.spring.springbootsamples.basic.usage.validation.valid;

import com.yzp.spring.springbootsamples.basic.usage.validation.model.UserVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidController {
    @RequestMapping("valid")
    public String test(@Valid @RequestBody UserVo u){

        return "adf";
    }



}
