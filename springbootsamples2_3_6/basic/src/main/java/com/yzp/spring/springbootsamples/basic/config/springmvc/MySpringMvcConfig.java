package com.yzp.spring.springbootsamples.basic.config.springmvc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration表明是个配置文件，启动时会加载
//自定义类MySpringMvcConfig重写WebMvcConfigurer中的一些方法
@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {

    /**
     * 拦截某个请求跳转固定位置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/templates/index.html").setViewName("index");
    }
    /**
     * @return
     * @Bean作用为注册对象 localeResolver方法名相当于对象的id，为固定值，此处表明注册的为LocaleResolver对象
     * LocaleResolver是spring用来获取区域信息，重写了这个对象用来配置国际化信息(中英文)
     */
    @Bean
    public LocaleResolver localeResolver() {
        //将mvc自动配置类 WebMvcConfigurer 的区域解析器替换为自定义的
        return new MyLocaleResoler();
    }
    /**
     * 设置自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor())//定义拦截执行的方法
                //添加需要拦截的路径
                .addPathPatterns("/**")
                //排除不需要拦截的路径
                .excludePathPatterns(
                        "/favicon.ico"
                );
    }
}
