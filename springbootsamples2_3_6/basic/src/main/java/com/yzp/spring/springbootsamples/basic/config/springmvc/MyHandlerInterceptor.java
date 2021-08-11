package com.yzp.spring.springbootsamples.basic.config.springmvc;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {
    /**
     * 调用路径方法前执行
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
//                response.sendRedirect("/fs/loginModule/loginInView");
            }
        } catch (NullPointerException e) {
            //不存在loginResult，跳到主页
//            response.sendRedirect("/fs/loginModule/loginInView");

        }
        return false;
    }
}
