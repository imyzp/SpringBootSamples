package com.yzp.spring.springbootsamples.poi.excel.template;

import com.yzp.spring.springbootsamples.poi.excel.template.celltype.ColumnInfo;
import org.apache.poi.hssf.record.ColumnInfoRecord;

import java.util.LinkedList;

/**
 * excel 模板
 */
public abstract class ExcelTemplate extends LinkedList<ColumnInfo> {
    /**
     * 定义导入sheet名称
     */
    private String sheetName;
    /**
     * 定义导入类型
     */
    private String importType;

    public String getSheetName() {
        return sheetName;
    }

    public ExcelTemplate setSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    public String getImportType() {
        return importType;
    }

    public ExcelTemplate setImportType(String importType) {
        this.importType = importType;
        return this;
    }

}
