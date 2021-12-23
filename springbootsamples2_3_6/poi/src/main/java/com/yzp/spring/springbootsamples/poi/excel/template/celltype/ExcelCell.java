package com.yzp.spring.springbootsamples.poi.excel.template.celltype;

import java.util.Arrays;
import java.util.List;

public class ExcelCell {
    private boolean notNull;
    private Integer maxLength;
    private Integer minLength;
    private String allowRegex;
    private List<String> enumArray;

    public ExcelCell() {
    }

    public ExcelCell(boolean notNull, Integer maxLength, Integer minLength, String allowRegex, String[] enumArray) {
        this.notNull = notNull;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.allowRegex = allowRegex;
        if(enumArray != null)
        {
            this.enumArray = Arrays.asList(enumArray);
        }
    }

    public ExcelCell(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public ExcelCell setNotNull(boolean notNull) {
        this.notNull = notNull;
        return this;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public ExcelCell setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public ExcelCell setMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    public String getAllowRegex() {
        return allowRegex;
    }

    public ExcelCell setAllowRegex(String allowRegex) {
        this.allowRegex = allowRegex;
        return this;
    }

    public List<String> getEnumArray() {
        return enumArray;
    }

    public ExcelCell setEnumArray(List<String> enumArray) {
        this.enumArray = enumArray;
        return this;
    }
}
