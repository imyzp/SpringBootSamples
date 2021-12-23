package com.yzp.spring.springbootsamples.es.controller;

import com.yzp.spring.springbootsamples.es.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EsController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        return contentService.parseContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> searchPage(@PathVariable("pageNo") int pageNo,
                                           @PathVariable("pageSize") int pageSize,
                                           @PathVariable("keyword") String keyword) throws IOException {
//        return contentService.searchPage(pageNo,pageSize,keyword);
        return contentService.searchLightPage(pageNo,pageSize,keyword);
    }

    @GetMapping("/search/suggest/{keyword}")
    public List<String> suggest(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.suggest(keyword);
    }
}
