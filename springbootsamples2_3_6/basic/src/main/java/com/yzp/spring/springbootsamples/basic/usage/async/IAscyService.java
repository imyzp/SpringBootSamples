package com.yzp.spring.springbootsamples.basic.usage.async;

import org.springframework.scheduling.annotation.Async;

public interface IAscyService {
    @Async
    void batchAdd();
}
