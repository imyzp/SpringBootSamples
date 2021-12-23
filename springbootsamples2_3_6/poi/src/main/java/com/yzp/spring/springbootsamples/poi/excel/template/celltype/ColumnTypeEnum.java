package com.yzp.spring.springbootsamples.poi.excel.template.celltype;

public enum ColumnTypeEnum {
    STRING_TYPE(1, "字段串类型"),
    DATE_TYPE(2, "Date时间类型"),
    INTEGER_TYPE(3, "Integer类型"),
    LONG_TYPE(4, "Long类型"),
    DOUBLE_TYPE(5, "Double类型"),
    FLOAT_TYPE(6, "Fload类型");
    private int value;
    private String name;

    ColumnTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public ColumnTypeEnum setValue(int value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public ColumnTypeEnum setName(String name) {
        this.name = name;
        return this;
    }
}
