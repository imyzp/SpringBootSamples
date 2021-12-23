package com.yzp.spring.springbootsamples.basic.mvc;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {
    /**
     * 调用路径方法前执行
     * 此处测试全部让通过 return true
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            //判断当前用户是否登录
            Boolean loginResult = Boolean.valueOf(request.getSession().getAttribute("").toString());
            if (loginResult) {
                // 已登录 放行
                return true;
            } else {
                // 未登录，跳转到登录页
//                response.sendRedirect("/fs/loginModule/loginInView");
                return true;
            }
        } catch (NullPointerException e) {
            // session不存在
//            response.sendRedirect("/fs/loginModule/loginInView");
        }
        return true;
    }
}
