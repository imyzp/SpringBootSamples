package com.yzp.spring.springbootsamples.base.model;

import org.elasticsearch.action.DocWriteResponse;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 添加文档结果
 * @Author yaozhenpeng
 * @Time 2022/2/26 21:25
 */
public class DocWriteRes {
    private String index;
    private String id;
    private DocWriteResponse.Result result;

    public String getIndex() {
        return index;
    }

    public DocWriteRes setIndex(String index) {
        this.index = index;
        return this;
    }

    public String getId() {
        return id;
    }

    public DocWriteRes setId(String id) {
        this.id = id;
        return this;
    }

    public DocWriteResponse.Result getResult() {
        return result;
    }

    public DocWriteRes setResult(DocWriteResponse.Result result) {
        this.result = result;
        return this;
    }
}
