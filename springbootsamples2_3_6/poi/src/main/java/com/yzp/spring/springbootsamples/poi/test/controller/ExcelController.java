package com.yzp.spring.springbootsamples.poi.test.controller;

import com.yzp.spring.springbootsamples.poi.test.service.ImportExcelServcie;
import com.yzp.spring.springbootsamples.poi.test.service.impl.ImportExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ExcelController {
    @Autowired
    ImportExcelServiceImpl importExcelService;

    /**
     * 导入文件
     * @param multiRequest
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/import/file")
    public ResponseEntity<?> file(MultipartHttpServletRequest multiRequest) throws Exception {
        return importExcelService.importExcel(multiRequest);
    }

    /**
     * 下载文件
     */
    @GetMapping("/export/file/{filePath}")
    public void export(@PathVariable(value = "filePath") String filePath, HttpServletResponse response){
        importExcelService.exportDetail(response,filePath);
    }

}
