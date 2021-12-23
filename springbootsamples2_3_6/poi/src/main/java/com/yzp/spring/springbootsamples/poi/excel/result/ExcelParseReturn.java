package com.yzp.spring.springbootsamples.poi.excel.result;

import java.util.List;

public class ExcelParseReturn<T,E> {
    private byte[] workbook;// 错误处理结果的文件
    private List<T> resultList;// 解析结果
    private int successCount;// 成功数量
    private int failedCount;// 错误数量
    private E dict;// 业务处理校验需要的基础数据

    public byte[] getWorkbook() {
        return workbook;
    }

    public ExcelParseReturn<T, E> setWorkbook(byte[] workbook) {
        this.workbook = workbook;
        return this;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public ExcelParseReturn<T, E> setResultList(List<T> resultList) {
        this.resultList = resultList;
        return this;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public ExcelParseReturn<T, E> setSuccessCount(int successCount) {
        this.successCount = successCount;
        return this;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public ExcelParseReturn<T, E> setFailedCount(int failedCount) {
        this.failedCount = failedCount;
        return this;
    }

    public E getDict() {
        return dict;
    }

    public ExcelParseReturn<T, E> setDict(E dict) {
        this.dict = dict;
        return this;
    }
}
