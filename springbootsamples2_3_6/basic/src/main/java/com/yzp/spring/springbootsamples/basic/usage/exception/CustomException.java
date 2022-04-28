package com.yzp.spring.springbootsamples.basic.usage.exception;

public class CustomException extends RuntimeException{
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
