package com.yzp.spring.springbootsamples.basic.usage.exception.controller;

import com.yzp.spring.springbootsamples.basic.usage.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    /**
     * 使用 spring的返回编码 ResponseStatus
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/globalexception")
    public String test2() throws CustomException {
        try{
            System.out.println(1/0);
        }catch (Exception e)
        {
            throw new CustomException(e.getMessage());
        }
        return "11";
    }
}
