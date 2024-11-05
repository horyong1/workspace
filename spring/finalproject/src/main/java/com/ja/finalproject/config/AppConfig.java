package com.ja.finalproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

import com.ja.finalproject.interceptor.SessionInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadFiles/**")
                .addResourceLocations("file:///C:/uploadFiles/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(sessionInterceptor)
            .addPathPatterns("/board/**")
            .excludePathPatterns("/board/mainPage"
            ,"/board/articleDetailPage"
            ,"/user/sessionNullPage")
            // .addPathPatterns("/board/writeArticlePage")
            // .addPathPatterns("/board/writeArticlePage")
            // .addPathPatterns("/board/writeArticlePage")
            ;

    }
}