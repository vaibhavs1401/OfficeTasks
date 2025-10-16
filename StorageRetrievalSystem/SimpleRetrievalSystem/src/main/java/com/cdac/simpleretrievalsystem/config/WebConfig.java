/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author hcdc
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cdac.simpleretrievalsystem.controller")
public class WebConfig implements WebMvcConfigurer {
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        var vr = new InternalResourceViewResolver();
        vr.setViewClass(JstlView.class);    
        vr.setPrefix("/WEB-INF/views/");
        vr.setSuffix(".jsp");
        return vr;
    }
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry reg) {
        reg.addResourceHandler("/css/**")
                .addResourceLocations("/css/");
        reg.addResourceHandler("/js/**")
                .addResourceLocations("/js/**");
    }
    
    
}

