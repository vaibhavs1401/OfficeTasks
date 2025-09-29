package com.cdac.student.config;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletRegistration;   // jakarta, not javax
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ DataConfig.class };  // JPA, tx, services
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };   // MVC layer
    }

    @Override
    protected String[] getServletMappings() { return new String[]{ "/" }; }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic reg) {
        reg.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        reg.setLoadOnStartup(1);
    }

    @Override
    protected Filter[] getServletFilters() {
        var enc = new CharacterEncodingFilter();
        enc.setEncoding("UTF-8");
        enc.setForceEncoding(true);
        return new Filter[]{ enc };
    }
}
