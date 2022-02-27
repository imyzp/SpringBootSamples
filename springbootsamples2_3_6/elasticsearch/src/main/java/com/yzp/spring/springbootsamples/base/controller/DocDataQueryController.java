package com.yzp.spring.springbootsamples.base.controller;

import com.yzp.spring.springbootsamples.base.service.QueryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 索引文档查询操作
 * @Author yaozhenpeng
 * @Time 2022/2/27 14:16
 */
@RestController
public class DocDataQueryController {
    @Autowired
    QueryTestService queryTestService;

    @GetMapping("/doc/matchAllQuery/{indexName}")
    public ResponseEntity getDocData(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(queryTestService.getDocData(indexName));
    }
    @GetMapping("/doc/termQuery/{indexName}")
    public ResponseEntity termQuery(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(queryTestService.termQuery(indexName));
    }
    @GetMapping("/doc/matchAllQuery/{indexName}/{pageNo}/{pageSize}")
    public ResponseEntity page(@PathVariable("indexName") String indexName,@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize) throws IOException {
        return ResponseEntity.ok(queryTestService.page(indexName,pageNo,pageSize));
    }
    @GetMapping("/doc/matchAllQuery/{indexName}/age/{sort}")
    public ResponseEntity sort(@PathVariable("indexName") String indexName,@PathVariable("sort") String sort) throws IOException {
        return ResponseEntity.ok(queryTestService.sort(indexName,sort));
    }
}
