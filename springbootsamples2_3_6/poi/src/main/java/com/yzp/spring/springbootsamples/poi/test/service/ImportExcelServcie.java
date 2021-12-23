package com.yzp.spring.springbootsamples.poi.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ImportExcelServcie {
    ResponseEntity<?> importExcel(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

}
