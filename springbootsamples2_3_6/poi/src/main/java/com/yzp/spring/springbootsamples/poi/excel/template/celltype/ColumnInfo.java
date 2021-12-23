package com.yzp.spring.springbootsamples.poi.excel.template.celltype;

public class ColumnInfo {
    private String cellName;
    private String columnName;
    private ExcelCell cellType;
    private String upperColumnName;
    private ColumnTypeEnum columnTypeEnum;

    public ColumnInfo(String cellName, String columnName, ExcelCell cellType, String upperColumnName, ColumnTypeEnum columnTypeEnum) {
        this.cellName = cellName;
        this.columnName = columnName;
        this.cellType = cellType;
        this.upperColumnName = upperColumnName;
        this.columnTypeEnum = columnTypeEnum;
    }

    public ColumnInfo(String cellName, String columnName, StringColumnCell cellType, ColumnTypeEnum columnTypeEnum) {
        this.cellName = cellName;
        this.columnName = columnName;
        this.cellType = cellType;
        this.upperColumnName = upperColumnName;
        this.columnTypeEnum = columnTypeEnum;
    }

    public ColumnInfo(String cellName, String columnName, EnumColumnCell cellType, ColumnTypeEnum columnTypeEnum) {
        this.cellName = cellName;
        this.columnName = columnName;
        this.cellType = cellType;
        this.columnTypeEnum = columnTypeEnum;
    }

    public String getCellName() {
        return cellName;
    }

    public ColumnInfo setCellName(String cellName) {
        this.cellName = cellName;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnInfo setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public ExcelCell getCellType() {
        return cellType;
    }

    public ColumnInfo setCellType(ExcelCell cellType) {
        this.cellType = cellType;
        return this;
    }

    public String getUpperColumnName() {
        if(upperColumnName == null)
        {
            char[] cs = columnName.toCharArray();
            cs[0]-=32;
            upperColumnName=String.valueOf(cs);
        }
        return upperColumnName;
    }

    public ColumnInfo setUpperColumnName(String upperColumnName) {
        this.upperColumnName = upperColumnName;
        return this;
    }

    public ColumnTypeEnum getColumnTypeEnum() {
        return columnTypeEnum;
    }

    public ColumnInfo setColumnTypeEnum(ColumnTypeEnum columnTypeEnum) {
        this.columnTypeEnum = columnTypeEnum;
        return this;
    }
}
