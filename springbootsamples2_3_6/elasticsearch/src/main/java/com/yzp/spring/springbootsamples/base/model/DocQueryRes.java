package com.yzp.spring.springbootsamples.base.model;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHits;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 查询索引文档数据内容返回
 * @Author yaozhenpeng
 * @Time 2022/2/27 14:42
 */
public class DocQueryRes {
    private SearchHits hits;
    private TimeValue took;
    private boolean timedOut;

    public SearchHits getHits() {
        return hits;
    }

    public DocQueryRes setHits(SearchHits hits) {
        this.hits = hits;
        return this;
    }

    public TimeValue getTook() {
        return took;
    }

    public DocQueryRes setTook(TimeValue took) {
        this.took = took;
        return this;
    }

    public boolean isTimedOut() {
        return timedOut;
    }

    public DocQueryRes setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
        return this;
    }
}
