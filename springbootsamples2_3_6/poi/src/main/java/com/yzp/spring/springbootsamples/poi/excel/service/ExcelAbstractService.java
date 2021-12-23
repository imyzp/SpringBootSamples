package com.yzp.spring.springbootsamples.poi.excel.service;

import com.yzp.spring.springbootsamples.poi.excel.exception.ExcelCustomException;
import com.yzp.spring.springbootsamples.poi.excel.result.ExcelParseReturn;
import com.yzp.spring.springbootsamples.poi.excel.template.ExcelTemplate;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.*;
import com.yzp.spring.springbootsamples.poi.excel.util.CleanPathUtil;
import com.yzp.spring.springbootsamples.poi.excel.util.ExcelDateFormatUtil;
import com.yzp.spring.springbootsamples.poi.excel.util.ExcelFileUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yzp.spring.springbootsamples.poi.excel.template.celltype.ColumnTypeEnum.*;

public abstract class ExcelAbstractService<T,E> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String zhPattern = "[\u4e00-\u9fa5]";
    private Class<T> entityClass;
    private Class<T> getT(){
        if(entityClass == null)
        {
            Type type = this.getClass().getGenericSuperclass();
            ParameterizedType ptype = (ParameterizedType) type;
            Type[] types = ptype.getActualTypeArguments();
            entityClass = (Class<T>) types[0];
        }
        return entityClass;
    }

    /**
     * 业务层判断对象参数是否正确
     * @param obj
     * @param dict
     * @param allData
     * @return
     */
    protected abstract String businessCheck(T obj, E dict, List<T> allData,boolean addFlag);

    /**
     * 初始化业务判断中需要的基础数据
     * @return
     */
    protected abstract E initCheckData();

    /**
     * 将导入的数据和记录，根据自己的业务保存起来
     * @param parseReturn
     * @param fileName
     * @return
     */
    protected abstract Object saveImportData(ExcelParseReturn parseReturn,String fileName);

    /**
     * 获取excel模板
     * @return
     */
    protected abstract ExcelTemplate getExcelTemplate();

    /**
     * 根据字段名生成map
     * @return
     */
    protected Map<String,ColumnInfo> getExcelTemplateMap(){
        Map<String,ColumnInfo> columnInfoMap = new HashMap<>();
        ExcelTemplate excelTemplate = getExcelTemplate();
        excelTemplate.forEach(e -> {
            columnInfoMap.put(e.getColumnName(),e);
        });
        return columnInfoMap;
    }

    protected ExcelParseReturn<T,E> parseExcelInputStream(String fileName, InputStream inputStream,boolean ifCheckError,int dataCount) throws IllegalAccessException, InstantiationException {
        ExcelTemplate excelTemplate = getExcelTemplate();
        if(excelTemplate == null)
        {
            return null;
        }
        Workbook workbook = null;
        try{
            try{
                workbook = ExcelFileUtils.getWorkBook(fileName,inputStream);
            }catch (Exception e)
            {
                log.error("【基础数据标准导入处理异常原因】：{}",e);
                throw new ExcelCustomException("导入文件格式异常，请校验。");
            }
            String sheetName = workbook.getSheetName(0);
            if(!excelTemplate.getSheetName().equals(sheetName))
            {
                throw new ExcelCustomException("表格中sheet名称不一致，请重新下载模板进行导入");
            }
            Sheet hssfSheet = workbook.getSheetAt(0);
            if(hssfSheet == null)
            {
                throw new ExcelCustomException("sheet不存在");
            }
            // 校验表头是否一致
            if(!ExcelFileUtils.checkCellName(hssfSheet,excelTemplate))
            {
                throw new ExcelCustomException("导入表头与模板不一致，请重新下载模板进行导入");
            }
            int allRowsNum = hssfSheet.getLastRowNum();
            List<T> allData = new ArrayList<T>();
            int failedCount = 0;
            int lastColunm = excelTemplate.size();
            if(allRowsNum -1 >= dataCount)
            {
                throw new ExcelCustomException("导入数据超出限制，仅能上传"+dataCount+"条数据。");
            }
            E dict = initCheckData();
            for(int rowNum = 1; rowNum <= allRowsNum; rowNum++)
            {
                StringBuffer resultMsg = new StringBuffer();
                Row hssfRow = hssfSheet.getRow(rowNum);
                T t = parseRow(hssfRow,excelTemplate,getT(),ifCheckError,dict,resultMsg);
                if(t != null)
                {
                    String checkMsg = businessCheck(t,dict,allData,true);
                    if(!StringUtils.isEmpty(checkMsg))
                    {
                        resultMsg.append(checkMsg);
                    }
                    log.info("resultMsg: {}",resultMsg);
                    allData.add(t);
                    if(!StringUtils.isEmpty(resultMsg.toString())){
                        Cell lastRow = hssfRow.getCell(lastColunm);
                        if(lastRow == null)
                        {
                            lastRow = hssfRow.createCell(lastColunm);
                        }
                        lastRow.setCellValue(resultMsg.toString());
                        failedCount++;
                    }
                }else{
                    failedCount++;
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            ExcelParseReturn<T, E> parseReturn = new ExcelParseReturn<>();
            parseReturn.setFailedCount(failedCount);

            parseReturn.setSuccessCount(allRowsNum - failedCount);
            parseReturn.setResultList(allData);
            parseReturn.setWorkbook(baos.toByteArray());
            parseReturn.setDict(dict);
            return parseReturn;
        }catch (IOException e)
        {
            log.error("file write error",e);
            throw new ExcelCustomException("文件获取异常");
        }finally {
            if(workbook != null)
            {
                try {
                    workbook.close();
                }catch (IOException e)
                {
                }
            }
            if(inputStream != null)
            {
                try{
                    inputStream.close();
                }catch (IOException e)
                {
                }
            }
        }
    }
    public <T> T parseRow(Row hssfRow,List<ColumnInfo> cellNames,Class<T> classType,boolean ifCheckError,E dict,StringBuffer resultMsg) throws IllegalAccessException
        ,InstantiationException{
        T obj = null;
        if(hssfRow != null)
        {
            try {
                obj = classType.newInstance();
            }catch (InstantiationException | IllegalAccessException e1)
            {
                throw e1;
            }
            int lastColunm = cellNames.size();
            StringBuffer errorMsg = new StringBuffer();
            for(int cellIndex=0;cellIndex<cellNames.size();cellIndex++)
            {
                Cell cell = hssfRow.getCell(cellIndex);
                ColumnInfo columnInfo = cellNames.get(cellIndex);

                Object value = null;
                if(cell != null && !StringUtils.isEmpty(cell.toString()))
                {
                    short format = cell.getCellStyle().getDataFormat();
                    switch(cell.getCellTypeEnum()){
                        case STRING:
                            value = cell.getRichStringCellValue().getString().trim();
                            break;
                        case NUMERIC:
                            if(ExcelDateFormatUtil.isCellDateFormatted(cell) || columnInfo.getColumnTypeEnum().equals(DATE_TYPE)){
                                Date d = DateUtil.getJavaDate(cell.getNumericCellValue());
                                value = sdf.format(d);
                                log.info("date:{}",value);
                            }else{
                                value = cell.getNumericCellValue();
                                if(String.valueOf(value).indexOf("E") != -1){
                                    value = new DecimalFormat("#").format(value);
                                }
                            }
                            break;
                        case BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString().trim();
                            break;
                    }
                }
                if(ifCheckError){
                    columnInfoCheck(columnInfo,value,errorMsg);
                }
                setPropertyValue(classType,columnInfo,obj,value,dict,ifCheckError,errorMsg);
            }
            if(!StringUtils.isEmpty(errorMsg.toString()))
            {
                resultMsg.append(errorMsg);
            }
        }
        return obj;
    }
    protected void columnInfoCheck(ColumnInfo columnInfo,Object value,StringBuffer errorMsg) {
        ExcelCell cellType = columnInfo.getCellType();
        if (cellType.isNotNull()) {
            if (value == null || "".equals(value.toString())) {
                errorMsg.append(columnInfo.getCellName()).append("字段不可为空");
            }
        }
        if (cellType instanceof EnumColumnCell || cellType instanceof StringColumnCell) {
            if (value != null && !StringUtils.isEmpty(value.toString())) {
                Integer maxLength = cellType.getMaxLength();
                if (maxLength != null) {
                    if (value.toString().length() > maxLength) {
                        errorMsg.append(columnInfo.getCellName()).append("字段限制长度最大为")
                                .append(maxLength).append(";");
                    }
                }
                Integer minLength = cellType.getMinLength();
                if (minLength != null) {
                    if (value.toString().length() < minLength) {
                        errorMsg.append(columnInfo.getCellName()).append("字段限制长度最小为")
                                .append(minLength).append(";");
                    }
                }
                String allowRegex = cellType.getAllowRegex();
                if (!StringUtils.isEmpty(allowRegex)) {
                    Pattern p = Pattern.compile(allowRegex);
                    Matcher m = p.matcher(value.toString());
                    if (!m.matches()) {
                        errorMsg.append(columnInfo.getCellName()).append("字段不满足校验规则;");
                    }
                }
                List<String> enumArray = cellType.getEnumArray();
                if (enumArray != null && enumArray.size() > 0) {
                    if(columnInfo.getColumnTypeEnum().equals(INTEGER_TYPE) && value instanceof Double){
                        value = Math.round((Double) value);
                    }
                    if (!enumArray.contains(value.toString())) {
                        errorMsg.append(columnInfo.getCellName()).append("字段输入不合规则;");
                    }
                }
            }
        } else if (cellType instanceof NumberColumnCell) {
            if (value != null) {
                boolean numeric = isNumber(String.valueOf(value));
                if(!numeric){
                    errorMsg.append((columnInfo.getCellName())).append("字段输入格式不正确").append(";");
                }else{
                    long minNum = ((NumberColumnCell) cellType).getMinNum();
                    BigDecimal vlong = new BigDecimal(String.valueOf(value));

                    if(vlong.compareTo(new BigDecimal(minNum))<0){
                        errorMsg.append(columnInfo.getCellName()).append("字段限制最小为").append(minNum).append(";");
                    }
                    long maxNum = ((NumberColumnCell) cellType).getMaxNum();
                    if(vlong.compareTo(new BigDecimal(maxNum))>0){
                        errorMsg.append(columnInfo.getCellName()).append("字段限制最大为").append(maxNum).append(";");
                    }
                }
            }
        }
    }

    /**
     * 是否是数字
     * @param str
     * @return
     */
    protected static boolean isNumber(String str){
        Pattern compile = Pattern.compile("-?[0-9]+(\\.[0-9]*)?");
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }

    protected void checkTemplateAndBiz(Class<T> classType,List<T> objList,StringBuffer errorMsg,List<String> filterColumn)
    {
        checkObj(classType,objList,errorMsg,filterColumn);
        E dict = initCheckData();
        List<T> hasCheckObj = new ArrayList<>();
        for(T obj: objList)
        {
            String error = businessCheck(obj,dict,hasCheckObj,false);
            if(Strings.isNotBlank(error)){
                errorMsg.append(error);
            }
            hasCheckObj.add(obj);
        }
    }

    /**
     * 根据模板校验对象
     * @param classType
     * @param objList
     * @param errorMsg
     * @param filterfColumn
     * @param <T>
     * @return
     */
    protected <T> List<T> checkObj(Class<T> classType, List<T> objList, StringBuffer errorMsg,List<String> filterfColumn)
    {
        ExcelTemplate excelTemplate = getExcelTemplate();
        for(T obj : objList){
            excelTemplate.forEach(e -> {
                try{
                    if(filterfColumn == null || !filterfColumn.contains(e.getColumnName()))
                    {
                        Method method = classType.getMethod("get" + e.getUpperColumnName());
                        if(method != null){
                            Object value = method.invoke(obj);
                            columnInfoCheck(e,value,errorMsg);
                        }
                    }
                }catch (NoSuchMethodException noSuchMethodException)
                {
                    noSuchMethodException.printStackTrace();
                }catch (InvocationTargetException invocationTargetException)
                {
                    invocationTargetException.printStackTrace();
                }catch (IllegalAccessException illegalAccessException)
                {
                    illegalAccessException.printStackTrace();
                }
            });
        }
        return objList;
    }

    /**
     * 设置属性参数
     * @param classType
     * @param columnInfo
     * @param obj
     * @param value
     * @param dict
     * @param ifCheckError
     * @param errorMsg
     * @param <T>
     */
    protected <T> void setPropertyValue(Class<T> classType, ColumnInfo columnInfo, T obj, Object value, E dict, boolean ifCheckError, StringBuffer errorMsg)
    {
        try{
            Object transformValue = invokePropertyValue(value,columnInfo,dict,ifCheckError,errorMsg);
            if(transformValue == null){
                return;
            }
            switch (columnInfo.getColumnTypeEnum()){
                case STRING_TYPE:
                    String str = String.valueOf(transformValue);
                    Method strMethod = classType.getMethod("set"+columnInfo.getUpperColumnName(),String.class);
                    if(strMethod != null)
                    {
                        strMethod.invoke(obj,str);
                    }
                    break;
                case DATE_TYPE:
                    try{
                        Date date = sdf.parse(String.valueOf(transformValue));
                        Method dateMethod = classType.getMethod("set"+columnInfo.getUpperColumnName(),
                                Date.class);
                        if(dateMethod != null)
                        {
                            dateMethod.invoke(obj,date);
                        }
                    }catch (ParseException e)
                    {
                        errorMsg.append(columnInfo.getCellName()).append("字段格式输入错误").append(";");
                    }
                    break;
                case INTEGER_TYPE:
                    if(isNumber(String.valueOf(transformValue))){
                        BigDecimal intv = new BigDecimal(String.valueOf(transformValue));
                        Method integerMethod = classType.getMethod("set"+columnInfo.getUpperColumnName(),Integer.class);
                        if(integerMethod != null)
                        {
                            integerMethod.invoke(obj,intv.intValue());
                        }
                    }
                    break;
                case LONG_TYPE:
                    if(isNumber(String.valueOf(transformValue))){
                        BigDecimal longv = new BigDecimal(String.valueOf(transformValue));
                        Method longMethod = classType.getMethod("set"+columnInfo.getUpperColumnName(),Long.class);
                        if(longMethod != null)
                        {
                            longMethod.invoke(obj,longv.longValue());
                        }
                    }
                    break;
                case DOUBLE_TYPE:
                    if(isNumber(String.valueOf(transformValue))){
                        BigDecimal douv = new BigDecimal(String.valueOf(transformValue));
                        Method doubleMethod = classType.getMethod("set"+columnInfo.getUpperColumnName(),Double.class);
                        if(doubleMethod != null)
                        {
                            doubleMethod.invoke(obj,douv.doubleValue());
                        }
                    }
                    break;
                case FLOAT_TYPE:
                    if(isNumber(String.valueOf(transformValue))){
                        BigDecimal flov = new BigDecimal(String.valueOf(transformValue));
                        Method floatMethod = classType.getMethod("set"+columnInfo.getUpperColumnName(),Double.class);
                        if(floatMethod != null)
                        {
                            floatMethod.invoke(obj,flov.floatValue());
                        }
                    }
                    break;
                default:
                    break;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * excel值转换成对象真正需要接收的值
     * @param value
     * @param columnInfo
     * @param dict
     * @param ifCheckError
     * @param errorMsg
     * @return
     * @throws ParseException
     */
    protected abstract Object invokePropertyValue(Object value,ColumnInfo columnInfo,E dict,boolean ifCheckError,StringBuffer errorMsg)
            throws ParseException;

    /**
     *
     * @param quertList
     * @return
     * @throws IOException
     */
    protected byte[] exportTemplate(List<T> quertList) throws IOException
    {
        ExcelTemplate excelTemplate = getExcelTemplate();
        if(excelTemplate == null)
        {
            return null;
        }
        Workbook workbook = null;
        try{
            workbook = ExcelFileUtils.createWork(excelTemplate.getSheetName(),excelTemplate);
            CellStyle textStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            textStyle.setDataFormat(format.getFormat("@"));
            if(quertList != null)
            {
                Sheet sheet = workbook.getSheetAt(0);
                for(int j = 0;j<quertList.size();j++)
                {
                    T queryObj = quertList.get(j);
                    Class classType = queryObj.getClass();
                    Row row = sheet.createRow(j + 1);
                    for(int i = 0;i<excelTemplate.size();i++)
                    {
                        Object value = null;
                        try{
                            Method method = classType.getMethod("get"+excelTemplate.get(i).getUpperColumnName());
                            if(method != null)
                            {
                                value = method.invoke(queryObj);
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        if(value != null)
                        {
                            Cell nowCell = row.createCell(i);

                            nowCell.setCellStyle(textStyle);
                            nowCell.setCellType(CellType.STRING);
                            nowCell.setCellValue(value.toString());
                        }else{
                            Cell nowCell = row.createCell(i);
                            nowCell.setCellStyle(textStyle);
                            nowCell.setCellType(CellType.STRING);
                        }
                    }
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            return baos.toByteArray();
        }finally {
            if(workbook != null)
            {
                try{
                    workbook.close();
                }catch (IOException e)
                {
                }
            }
        }
    }
    // 通用导出excel 数据
    protected void excelData(HttpServletResponse response,List<T> quertList)
    {
        ExcelTemplate template = getExcelTemplate();
        ServletOutputStream outputStream = null;
        String fileName = new StringBuffer().append("attachment;filename=").append(template.getImportType()).append(".xlsx").toString();
        try{
            byte[] bytes = exportTemplate(quertList);
            response.setHeader("Content-disposition",fileName);
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }catch (IOException e)
        {
            throw new ExcelCustomException("导出数据错误！");
        }
    }
    protected void setDownloadResponseHearder(String scenesName,HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/download;charset=UTF-8");
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        try{
            Pattern p = Pattern.compile(zhPattern);
            Matcher m = p.matcher(scenesName);
            StringBuffer b = new StringBuffer();
            while (m.find())
            {
                m.appendReplacement(b, URLEncoder.encode(m.group(0), StandardCharsets.UTF_8.toString()));
            }
            m.appendTail(b);
            String scenesNameSci = b.toString();
            response.setHeader("Cache-Control","must-revalidate,post-check=0,pre-check=0");
            response.setHeader("Content-disposition","attachment;filename=\""+scenesNameSci+"\"");
        }catch (UnsupportedEncodingException e)
        {

        }
        response.setStatus(201);
    }
    protected void setDefaultResponseHearder(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
    }
    protected Date parseString2Date(String dateStr)
    {
        Date d = new Date();
        d.setTime(Long.parseLong(dateStr));
        return d;
    }
    public void exportDetail(HttpServletResponse response,String file_path){
        String file_name = file_path.substring(file_path.lastIndexOf("/") + 1);
        File file = null;
        DataOutputStream tmps = null;
        DataInputStream bis = null;
        ServletOutputStream out = null;
        FileInputStream fis = null;
        try{
            byte[] fileNameBytes = file_name.getBytes(StandardCharsets.UTF_8);
            file_name = new String(fileNameBytes,0,fileNameBytes.length,StandardCharsets.ISO_8859_1);
            file = new File(file_path);
            out = response.getOutputStream();
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/plain");
            response.setHeader("Content-disposition","attachment;filename="+file_name);
            response.addHeader("Content-length",String.valueOf(file.length()));
            fis = new FileInputStream(file);
            bis = new DataInputStream(fis);
            tmps = new DataOutputStream(out);
            byte[] buf = new byte[8096];
            while (bis.read(buf) != -1)
            {
                tmps.write(buf);
            }
            tmps.flush();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(null != tmps)
            {
                try{
                    tmps.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(null != bis)
            {
                try{
                    bis.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            if(null != fis)
            {
                try{
                    fis.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(null != out)
            {
                try{
                    out.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    private void checkDir(String savePath)
    {
        savePath = CleanPathUtil.cleanString(savePath);
        File file = new File(savePath);
        if(!file.exists())
        {
            file.mkdirs();
        }
    }
    protected  void exportImportHistoryDetail(HttpServletResponse response,String filePath)
    {
        String file_name = filePath.substring(filePath.lastIndexOf("/")+1);
        ServletOutputStream outputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = ExcelFileUtils.getWorkBook(file_name,fileInputStream);
            setDownloadResponseHearder(file_name,response);
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        }catch (Exception e)
        {
            throw new ExcelCustomException("导出下载结果错误");
        }finally {
            try{
                outputStream.close();
                baos.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }





}
