package com.yzp.spring.springbootsamples.poi.excel.util;

import com.yzp.spring.springbootsamples.poi.excel.exception.ExcelCustomException;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.ColumnInfo;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExcelFileUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelFileUtils.class);
    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";
    public static Workbook checkWorkBook(String fileName, InputStream inputStream){
        Workbook workbook = null;
        try{
            workbook = getWorkBook(fileName,inputStream);
        }catch (Exception e){
            throw new ExcelCustomException("导入文件格式异常，请校验");
        }
        return workbook;
    }

    /**
     * 校验文件
     * @param files
     * @param limitSize
     * @param checkFileSize
     * @param checkFileType
     * @return
     */
    public static InputStream checkFile(MultipartFile files,long limitSize,boolean checkFileSize,boolean checkFileType){
        if(checkFileSize)
        {
            long s = files.getSize();
            if(s>limitSize)
            {
                throw new ExcelCustomException("上传文件超过"+limitSize);
            }
        }
        InputStream inputStream = null;
        try{
            inputStream = files.getInputStream();
        }catch (IOException e)
        {
            throw new ExcelCustomException("获取文件异常");
        }
        String fileName = files.getOriginalFilename();
        // 判断文件是否存在
        if(null == fileName)
        {
            logger.error("文件不存在！");
            throw new ExcelCustomException("文件不存在！");
        }
        if(checkFileType){
            if(!fileName.endsWith(XLS) && !fileName.endsWith(XLSX))
            {
                logger.error("{}不是excel文件",fileName);
                throw new ExcelCustomException(fileName+"不是excel文件");
            }
        }
        return inputStream;
    }

    /**
     * 获取工作薄
     * @param fileName
     * @param inputStream
     * @return
     */
    public static Workbook getWorkBook(String fileName,InputStream inputStream)
    {
        // 创建 Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try{
            // 根据文件后缀名不同（xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(XLS))
            {
                // 2003
                workbook = new HSSFWorkbook(inputStream);
            }else if(fileName.endsWith(XLSX))
            {
                // 2007
                workbook = new XSSFWorkbook(inputStream);
            }
        }catch (IOException e)
        {
            logger.error("create workbook error",e);
        }
        return workbook;
    }
    public static Boolean checkCellName(Sheet hssfSheet, List<ColumnInfo> cellNames){
        Row row = hssfSheet.getRow(0);
        if(row == null){
            throw new ExcelCustomException("表头行不可为空！");
        }
        int column = row.getPhysicalNumberOfCells();
        int count = 0;
        for(int j=0;j<column;j++){
            if(row!=null && row.getCell(j) != null && Strings.isNotBlank(row.getCell(j).toString()))
            {
                count ++;
            }
        }
        // 判断个数
        if(cellNames == null || cellNames.size() != count){
            return Boolean.FALSE;
        }
        for(int i=0;i<count;i++){
            String sourceCell = row.getCell(i).toString();
            ColumnInfo targetCell = cellNames.get(i);
            if(!sourceCell.equals(targetCell.getCellName()))
            {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
    public static  Workbook createWork(String sheetName,List<ColumnInfo> cellNames){
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        Row firstRow = sheet.createRow(0);
        ForEachUtils.forEach(0,cellNames,(index,e)->{
            firstRow.createCell(index).setCellValue(e.getCellName());
        });
        return workbook;
    }
}
