package com.yzp.spring.springbootsamples.poi.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import static org.apache.poi.ss.usermodel.DateUtil.isInternalDateFormat;
import static org.apache.poi.ss.usermodel.DateUtil.isValidExcelDate;

public class ExcelDateFormatUtil {
    public static boolean isCellDateFormatted(Cell cell) {
        if (cell == null) {
            return false;
        }
        boolean bDate = false;
        double d = cell.getNumericCellValue();
        if (isValidExcelDate(d)) {
            CellStyle style = cell.getCellStyle();
            if (style == null) {
                return false;
            }
            int i = style.getDataFormat();
            String f = style.getDataFormatString();
            bDate = isADateFormat(i, f);
        }
        return bDate;
    }

    public static boolean isADateFormat(int formatIndex, String formatString) {
        if (isInternalDateFormat(formatIndex)) {
            return true;
        }
        if ((formatString == null) || (formatString.length() == 0)) {
            return false;
        }
        String fs = formatString;
        // 下面这一行是自己手动添加的以支持汉字格式wingzing
        fs = fs.replaceAll("[\"|\']", "").replaceAll("[年|月|日|分|秒|毫秒|微秒]", "");
        fs = fs.replaceAll("\\\\-", "-");
        fs = fs.replaceAll("\\\\,", ",");
        fs = fs.replaceAll("\\\\.", ".");
        fs = fs.replaceAll("\\\\ ", " ");
        fs = fs.replaceAll(";@", "");
        fs = fs.replaceAll("^\\[\\$\\-.*?\\]", "");
        fs = fs.replaceAll("^\\[[a-zA-Z]+\\]", "");
//        fs = fs.replaceAll();
        boolean matches = fs.matches("^[yYmMdDhHsS\\-/,. :]+[ampAMP/]*$");
        return matches;
    }

    public static boolean isInternalDateFormat(int format) {
        switch (format) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 45:
            case 46:
            case 57:
            case 58:
            case 31:
            case 32:
                return true;
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:


            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
        }
        return false;
    }
    public static boolean isValidExcelDate(double value)
    {
        return (value > -4.940656458412465E-324D);
    }


}
