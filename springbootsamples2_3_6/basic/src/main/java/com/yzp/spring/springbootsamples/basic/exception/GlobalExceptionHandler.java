package com.yzp.spring.springbootsamples.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * 统一异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> validExceptionHandler(CustomException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            SQLException.class,
            NullPointerException.class
    })
    public ResponseEntity<?> methodException(Exception e)
    {
        if(e instanceof  SQLException){
            return ResponseEntity.badRequest().body("数据库异常");
        }else if(e instanceof  NullPointerException){
            return ResponseEntity.badRequest().body("空指针异常");
        }else{
            return ResponseEntity.badRequest().body("系统未知异常");
        }
    }
}
