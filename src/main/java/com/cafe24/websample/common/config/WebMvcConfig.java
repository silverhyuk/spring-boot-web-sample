package com.cafe24.websample.common.config;

import com.cafe24.websample.common.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/**")
                .addPathPatterns("/**/*")
                .excludePathPatterns("/test/**/")
                .excludePathPatterns("/login.ws"); //로그인 쪽은 예외처리를 한다.
    }
}