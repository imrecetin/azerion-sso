package com.azerion.sso.config;

import com.azerion.sso.controller.interceptor.M2MClientIDValidationInterceptor;
import com.azerion.sso.controller.interceptor.M2MRequestParametersInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    M2MRequestParametersInterceptor m2MRequestParametersInterceptor;

    @Autowired
    M2MClientIDValidationInterceptor m2MClientIDValidationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(m2MRequestParametersInterceptor).addPathPatterns("/m2m/**");
        registry.addInterceptor(m2MClientIDValidationInterceptor).addPathPatterns("/m2m/**");
    }
}
