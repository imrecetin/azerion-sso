package com.azerion.sso.config;

import com.azerion.sso.controller.interceptor.CommonHeaderParameterInterceptor;
import com.azerion.sso.controller.interceptor.M2MClientIDValidationInterceptor;
import com.azerion.sso.controller.interceptor.M2MRequestParametersToHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    M2MRequestParametersToHeaderInterceptor m2MRequestParametersToHeaderInterceptor;

    @Autowired
    M2MClientIDValidationInterceptor m2MClientIDValidationInterceptor;

    @Autowired
    CommonHeaderParameterInterceptor commonHeaderParameterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonHeaderParameterInterceptor).addPathPatterns("/**");
        registry.addInterceptor(m2MRequestParametersToHeaderInterceptor).addPathPatterns("/api/**/m2m/**");
        registry.addInterceptor(m2MClientIDValidationInterceptor).addPathPatterns("/api/**/m2m/**");
    }
}
