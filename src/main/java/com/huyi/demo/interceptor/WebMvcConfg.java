package com.huyi.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
     /*   registry.addInterceptor(testInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/api/**", "/assets/**");*/
    }
}

