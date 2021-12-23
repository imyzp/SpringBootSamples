package com.yzp.spring.springbootsamples.poi.test.service.impl;

import com.yzp.spring.springbootsamples.poi.excel.result.ExcelParseReturn;
import com.yzp.spring.springbootsamples.poi.excel.service.ExcelAbstractService;
import com.yzp.spring.springbootsamples.poi.excel.template.ExcelTemplate;
import com.yzp.spring.springbootsamples.poi.excel.template.celltype.ColumnInfo;
import com.yzp.spring.springbootsamples.poi.excel.util.ExcelFileUtils;
import com.yzp.spring.springbootsamples.poi.test.service.ImportExcelServcie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

@Service
public class ImportExcelServiceImpl extends ExcelAbstractService implements ImportExcelServcie {

    @Override
    public ResponseEntity<?> importExcel(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        Assert.notNull(file,"解析文件不存在");
        String fileName = file.getOriginalFilename();
        InputStream inputStream = ExcelFileUtils.checkFile(file, 1024 * 1024 * 5, true, true);
        ExcelParseReturn parseReturn = parseExcelInputStream(fileName, inputStream, true, 50000);
        if(parseReturn == null){
            throw new Exception("解析异常");
        }
        Object o = saveImportData(parseReturn, fileName);
        return ResponseEntity.ok(o);
    }

    @Override
    protected String businessCheck(Object obj, Object dict, List allData, boolean addFlag) {
        return null;
    }

    @Override
    protected Object initCheckData() {
        return null;
    }

    @Override
    protected Object saveImportData(ExcelParseReturn parseReturn, String fileName) {
        return null;
    }

    @Override
    protected ExcelTemplate getExcelTemplate() {
        return null;
    }

    @Override
    protected Object invokePropertyValue(Object value, ColumnInfo columnInfo, Object dict, boolean ifCheckError, StringBuffer errorMsg) throws ParseException {
        return null;
    }
}
