package com.yzp.spring.springbootsamples.poi.excel.template.celltype;

public class StringColumnCell extends ExcelCell{
    private static StringColumnCell hasStr = null;
    private static StringColumnCell hasStr200 = null;
    private static StringColumnCell nullStr = null;
    private static StringColumnCell nullStr200 = null;
    private static StringColumnCell nullStr1000 = null;
    public StringColumnCell(boolean notNull){
        super(notNull,null,null,null,null);
    }
    public StringColumnCell(boolean notNull,Integer minLength,Integer maxLength)
    {
        super(notNull,minLength,maxLength,null,null);
    }

    public StringColumnCell(boolean notNull, Integer maxLength) {
        super(notNull,null,maxLength,null,null);
    }

    public static StringColumnCell hasStr(){
        if(hasStr == null)
        {
            hasStr = new StringColumnCell(true);
        }
        return hasStr;
    }
    public static StringColumnCell hasStr200(){
        if(hasStr200 == null)
        {
            hasStr200 = new StringColumnCell(true,200);
        }
        return hasStr200;
    }
    public static StringColumnCell nullStr(){
        if(nullStr == null)
        {
            nullStr = new StringColumnCell(false);
        }
        return nullStr;
    }
    public static StringColumnCell nullStr200(){
        if(nullStr200 == null)
        {
            nullStr200 = new StringColumnCell(false,200);
        }
        return nullStr200;
    }
    public static StringColumnCell nullStr1000(){
        if(nullStr1000 == null)
        {
            nullStr1000 = new StringColumnCell(false,1000);
        }
        return nullStr1000;
    }


}
