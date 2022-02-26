package com.yzp.spring.springbootsamples.base.model;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 查询文档结果
 * @Author yaozhenpeng
 * @Time 2022/2/26 21:54
 */
public class DocGetRes {
    private String index;
    private String type;
    private String id;
    private String sourceAsString;

    public String getIndex() {
        return index;
    }

    public DocGetRes setIndex(String index) {
        this.index = index;
        return this;
    }

    public String getType() {
        return type;
    }

    public DocGetRes setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public DocGetRes setId(String id) {
        this.id = id;
        return this;
    }

    public String getSourceAsString() {
        return sourceAsString;
    }

    public DocGetRes setSourceAsString(String sourceAsString) {
        this.sourceAsString = sourceAsString;
        return this;
    }
}
