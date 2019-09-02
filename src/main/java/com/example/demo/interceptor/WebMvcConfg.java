package com.example.demo.interceptor;

import com.example.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截的请求，并排除几个不拦截的请求
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/index", "/css/*", "/login");
    }


}
