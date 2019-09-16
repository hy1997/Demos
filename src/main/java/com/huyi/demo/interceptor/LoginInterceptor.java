package com.huyi.demo.interceptor;

import com.huyi.demo.Utils.StringUtils;
import com.huyi.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * * 自定义拦截器-基于springmvc
 * * @ClassName: CustomInterceptor
 * * @Description: springMVC项目中的拦截器，它拦截的目标是请求的地址，比MethodInterceptor先执行。
 * *                 该拦截器只能过滤action请求，SPring允许多个拦截器同时存在，通过拦截器链管理。
 * *                 当preHandle return true时，执行下一个拦截器，直到所有拦截器执行完，再运行被拦截的请求。
 * *                 当preHandle return false时, 不再执行后续的拦截器链及被拦截的请求。
 * * @author OnlyMate
 * * @Date 2018年8月28日 下午2:30:22
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) redisUtil.get("username");
        if (StringUtils.isEmpty(username)) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("TestInterceptor postHandle....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("TestInterceptor afterCompletion....");
    }

}
