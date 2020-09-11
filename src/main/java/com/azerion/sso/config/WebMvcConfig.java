package com.azerion.sso.config;

import com.azerion.sso.controller.interceptor.*;
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

    @Autowired
    JWTTokenValidationInterceptor jwtTokenValidationInterceptor;

    @Autowired
    AuthTypeHeaderParameterInterceptor authTypeHeaderParameterInterceptor;

    @Autowired
    M2MAddCridentialInterceptor m2mAddCridentialInterceptor;

    @Autowired
    EndUserAddCridentialInterceptor endUserAddCridentialInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonHeaderParameterInterceptor).addPathPatterns("/**");
        registry.addInterceptor(m2MRequestParametersToHeaderInterceptor).addPathPatterns("/api/**/m2m/**");
        registry.addInterceptor(m2MClientIDValidationInterceptor).addPathPatterns("/api/**/m2m/**");
        registry.addInterceptor(jwtTokenValidationInterceptor).addPathPatterns("/api/**/end-user/**");
        registry.addInterceptor(jwtTokenValidationInterceptor).addPathPatterns("/api/**/admin/**");
        registry.addInterceptor(authTypeHeaderParameterInterceptor).addPathPatterns("/api/**/m2m/**");
        registry.addInterceptor(authTypeHeaderParameterInterceptor).addPathPatterns("/api/**/end-user/**");
        registry.addInterceptor(authTypeHeaderParameterInterceptor).addPathPatterns("/api/**/admin/**");
        registry.addInterceptor(m2mAddCridentialInterceptor).addPathPatterns("/api/**/m2m/**");
        registry.addInterceptor(endUserAddCridentialInterceptor).addPathPatterns("/api/**/end-user/**");
    }
}
