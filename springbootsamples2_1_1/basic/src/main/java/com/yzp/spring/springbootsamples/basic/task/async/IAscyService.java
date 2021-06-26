package com.yzp.spring.springbootsamples.basic.task.async;

import org.springframework.scheduling.annotation.Async;

public interface IAscyService {
    @Async
    void batchAdd();
}
