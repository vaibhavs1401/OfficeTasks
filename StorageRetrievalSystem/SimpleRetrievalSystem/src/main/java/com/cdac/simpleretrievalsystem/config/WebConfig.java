package com.cdac.simpleretrievalsystem.config;

import com.cdac.simpleretrievalsystem.interceptor.CookieConsentInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.cdac.simpleretrievalsystem.controller",
        "com.cdac.simpleretrievalsystem.service",
        "com.cdac.simpleretrievalsystem.repository",
        "com.cdac.simpleretrievalsystem.interceptor"
})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CookieConsentInterceptor cookieConsentInterceptor; // Spring-managed interceptor

    // View Resolver
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setViewClass(JstlView.class);
        vr.setPrefix("/WEB-INF/views/");
        vr.setSuffix(".jsp");
        return vr;
    }

    // Static resources mapping
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/Themes/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/Themes/js/");
    }

    // Register interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieConsentInterceptor)
                .addPathPatterns("/**");
    }

    // ObjectMapper for JSON handling
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }
}
