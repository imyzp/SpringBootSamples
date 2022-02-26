package com.yzp.spring.springbootsamples.base.controller;

import com.yzp.spring.springbootsamples.base.service.IndexTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createIndex/{indexName}")
    public ResponseEntity createIndex(@PathVariable("indexName") String indexName) throws IOException {
        boolean acknowledged = indexTestService.createIndex(indexName);
        return ResponseEntity.ok(acknowledged);
    }
    @GetMapping("/indexDetail/{indexName}")
    public ResponseEntity queryIndex(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(indexTestService.indexDetail(indexName));
    }
    @DeleteMapping("/deleteIndex/{indexName}")
    public ResponseEntity deleteIndex(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(indexTestService.deleteIndex(indexName));
    }
}
