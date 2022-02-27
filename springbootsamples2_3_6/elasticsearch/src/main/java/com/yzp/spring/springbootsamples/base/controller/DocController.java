package com.yzp.spring.springbootsamples.base.controller;

import com.yzp.spring.springbootsamples.base.service.DocTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 索引文档操作
 * @Author yaozhenpeng
 * @Time 2022/2/26 21:28
 */
@RestController
public class DocController {
    @Autowired
    DocTestService docTestService;

    @PostMapping("/addDoc/{indexName}")
    public ResponseEntity addDoc(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(docTestService.addDoc2Index(indexName));
    }
    @PostMapping("/batchAddDoc/{indexName}")
    public ResponseEntity batchAddDoc(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(docTestService.batchAddDoc2Index(indexName));
    }
    @PutMapping("/updateDoc/{indexName}")
    public ResponseEntity updateDoc(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(docTestService.updateDocByIndex(indexName));
    }
    @GetMapping("/getDoc/{indexName}")
    public ResponseEntity getDoc(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(docTestService.getDoc(indexName));
    }
    @DeleteMapping("/batchDeleteDoc/{indexName}")
    public ResponseEntity batchDeleteDoc(@PathVariable("indexName") String indexName) throws IOException {
        return ResponseEntity.ok(docTestService.batchDeleteDoc(indexName));
    }
}
