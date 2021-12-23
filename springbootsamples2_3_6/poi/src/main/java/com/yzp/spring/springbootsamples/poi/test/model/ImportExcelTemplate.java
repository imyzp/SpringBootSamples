package com.yzp.spring.springbootsamples.poi.test.model;

import com.yzp.spring.springbootsamples.poi.excel.template.ExcelTemplate;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.ColumnInfo;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.ColumnTypeEnum;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.EnumColumnCell;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.StringColumnCell;

public class ImportExcelTemplate extends ExcelTemplate {
    private static ImportExcelTemplate importExcelTemplate;

    public ImportExcelTemplate() {
    }

    public static ImportExcelTemplate getInstance(){
         if(importExcelTemplate == null){
             importExcelTemplate = new ImportExcelTemplate();
             importExcelTemplate.setSheetName("导入sheet名称");
             importExcelTemplate.setImportType("excel导入例子");

             importExcelTemplate.add(new ColumnInfo("字符串列","column1",new StringColumnCell(true,20), ColumnTypeEnum.STRING_TYPE));
             importExcelTemplate.add(new ColumnInfo("下拉列","column2",new EnumColumnCell(true,new String[]{"A","B"}), ColumnTypeEnum.STRING_TYPE));

         }
         return importExcelTemplate;
    }
}
