package com.kexin.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ForkJoinPool;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //用户登录成功后,应该有自己的session
        Object session = request.getSession().getAttribute("LoginUser");
        // 访问页面时首先判断session中有没有用户的信息
        if (session == null) {   // 如果没有，拦截器进行拦截
            request.setAttribute("msg", "权限不够,请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            // 如果有，拦截器放行
            return true;
        }
    }
}