package com.yzp.spring.springbootsamples.basic.cors;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  使CorsFilter生效，在Application中注册到spring的FilterRegistrationBean中
 * 跨域拦截器
 */
public class CorsFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

//        String curOrigin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin", curOrigin);
        response.setHeader("Access-Control-Allow-Origin", "http://www.domain1.com");

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "3600");

        // 表明服务器支持的所有头信息字段
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires," +
                " Content-Type, X-E4M-With,userId,token");
        // 如果要把Cookie发到服务器，需要指定Access-Control-Allow-Credentials字段true;
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("XDomainRequestAllowed", "1");
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {

    }
}
