package com.yzp.spring.springbootsamples.base.controller;

import com.yzp.spring.springbootsamples.base.service.IndexTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 索引操作
 * @Author yaozhenpeng
 * @Time 2022/2/26 19:20
 */
@RestController
public class IndexController {
    @Autowired
    IndexTestService indexTestService;

    @GetMapping("/createIndex/{indexName}")
    public ResponseEntity createIndex(@PathVariable("indexName") String indexName) throws IOException {
        boolean index = indexTestService.createIndex(indexName);
        return ResponseEntity.ok(index);
    }
}
