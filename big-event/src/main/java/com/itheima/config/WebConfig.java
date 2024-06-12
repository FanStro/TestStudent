package com.itheima.config;

import com.itheima.interceptors.Logininterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: big-event
 * @description: WebConfig
 * @author: 樊福蕰
 * @create: 2024-05-21 11:30
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private  Logininterceptor logininterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(logininterceptor).excludePathPatterns("/user/login","/user/register");
    }
}
