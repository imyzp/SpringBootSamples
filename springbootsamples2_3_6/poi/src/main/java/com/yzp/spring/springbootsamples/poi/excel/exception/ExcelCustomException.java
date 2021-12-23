package com.yzp.spring.springbootsamples.poi.excel.exception;

public class ExcelCustomException extends RuntimeException{
    private Integer code;
    private String message;

    public ExcelCustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExcelCustomException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public ExcelCustomException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public ExcelCustomException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public ExcelCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    public ExcelCustomException(String message) {
        super(message);
    }
}
