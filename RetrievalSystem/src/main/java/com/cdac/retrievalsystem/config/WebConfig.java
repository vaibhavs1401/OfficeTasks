/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.retrievalsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/**
 *
 * @author hcdc
 */


@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = "org.cdac.retrievalsystem")
@PropertySource("classpath:application.properties")
public class WebConfig implements WebMvcConfigurer{
    
    
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setCache(false);
        return viewResolver;
    }
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/theme/**").addResourceLocations("/theme/");
         registry.addResourceHandler("/baselocation/**").addResourceLocations("http://localhost:81");
    }
    
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
    
//    @Bean(name = "properties")
//    public PropertiesFactoryBean properties() {
//        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
//        String userHome = System.getProperty("user.home");
//        factoryBean.setLocation(
//            new FileSystemResource(userHome + "/.sanskriti/conf/exposed.properties")
//        );
//        return factoryBean;
//    }
//    
    
@Bean
public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
}

        
    
}
