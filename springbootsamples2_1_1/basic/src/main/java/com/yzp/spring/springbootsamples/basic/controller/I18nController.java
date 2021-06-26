package com.yzp.spring.springbootsamples.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试国际化配置
 * @return
 */
@RestController
public class I18nController {
    @GetMapping("/i18n")
    public String i18n() {
        return "i18n/i18n";
    }
}
