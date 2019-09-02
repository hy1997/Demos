package com.example.demo.interceptor;

import com.example.redis.RedisUtil;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Object username = redisUtil.get("username");
        // 如果获取的request的session中的loginUser参数为空（未登录），就返回登录页，否则放行访问
        if (username == null) {
            // 未登录，给出错误信息，
            request.setAttribute("msg", "无权限请先登录");
            // 获取request返回页面到登录页
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        } else {
            // 已登录，放行
            return true;
        }
    }
}
