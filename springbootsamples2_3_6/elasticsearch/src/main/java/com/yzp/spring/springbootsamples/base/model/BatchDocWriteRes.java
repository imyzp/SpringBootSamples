package com.yzp.spring.springbootsamples.base.model;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.common.unit.TimeValue;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 批量添加
 * @Author yaozhenpeng
 * @Time 2022/2/27 9:54
 */
public class BatchDocWriteRes {
    private TimeValue took;
    private BulkItemResponse[] items;

    public TimeValue getTook() {
        return took;
    }

    public BatchDocWriteRes setTook(TimeValue took) {
        this.took = took;
        return this;
    }

    public BulkItemResponse[] getItems() {
        return items;
    }

    public BatchDocWriteRes setItems(BulkItemResponse[] items) {
        this.items = items;
        return this;
    }
}
