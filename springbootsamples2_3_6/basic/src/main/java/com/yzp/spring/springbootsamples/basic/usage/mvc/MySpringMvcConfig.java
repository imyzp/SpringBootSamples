package com.yzp.spring.springbootsamples.basic.usage.mvc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义类 MySpringMvcConfig重写WebMvcConfigurer中的一些方法
 */
@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {

    /**
     * 页面跳转
     * 拦截某个请求跳转固定位置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("拦截某个请求跳转固定位置");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/templates/index.html").setViewName("index");
    }

    /**
     * 拦截器
     * 设置自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("设置自定义拦截器");
        registry.addInterceptor(new MyHandlerInterceptor())//定义拦截执行的方法
                //添加需要拦截的路径
                .addPathPatterns("/**")
                //排除不需要拦截的路径
                .excludePathPatterns(
                        "/favicon.ico"
                );
    }


}
