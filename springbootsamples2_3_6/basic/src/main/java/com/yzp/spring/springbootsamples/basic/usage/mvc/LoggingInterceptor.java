package com.yzp.spring.springbootsamples.basic.usage.mvc;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description
 * @Author yaozhenpeng
 * @Time 2021/11/29 22:52
 */
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        return null;
    }
}
