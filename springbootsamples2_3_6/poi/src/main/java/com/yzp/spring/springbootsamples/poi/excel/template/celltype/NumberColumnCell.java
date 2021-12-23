package com.yzp.spring.springbootsamples.poi.excel.template.celltype;

public class NumberColumnCell extends ExcelCell{
    private long maxNum;
    private long minNum;

    public NumberColumnCell(long maxNum, long minNum) {
        this.maxNum = maxNum;
        this.minNum = minNum;
    }

    public NumberColumnCell(boolean notNull,long maxNum, long minNum) {
        super(notNull);
        this.maxNum = maxNum;
        this.minNum = minNum;
    }

    public long getMaxNum() {
        return maxNum;
    }

    public NumberColumnCell setMaxNum(long maxNum) {
        this.maxNum = maxNum;
        return this;
    }

    public long getMinNum() {
        return minNum;
    }

    public NumberColumnCell setMinNum(long minNum) {
        this.minNum = minNum;
        return this;
    }
}
