package com.yzp.spring.springbootsamples.basic.i18n;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * LocaleResolver 是spring用来获取区域信息对象Locale,Locale用来切换国际化信息(区域信息就像zh_CH/en_US)
 * 重写LocaleResolver，自定义MyLocaleResoler来切换国际化信息
 */
@Configuration
public class MyLocaleResoler implements LocaleResolver {
    /**
     * 解析区域信息
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        System.out.println("解析区域信息");
        //获取请求头中language的参数值
        String language = httpServletRequest.getParameter("language");
        //获取浏览器上的区域信息
        Locale locale = httpServletRequest.getLocale();

        //参数language有值，则用language作用区域信息
        if (!StringUtils.isEmpty(language)) {
            //将如zh_CH分割
            String[] split = language.split("_");
            //设置区域信息,如zh_CH的语言代码为split[0]对应zh，国家代码为split[1]对应CH
            locale = new Locale(split[0], split[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
