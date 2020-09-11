package com.azerion.sso.controller.interceptor;


import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.WebUtils;
import com.azerion.sso.exception.InValidJwtTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTTokenValidationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MutableHttpServletRequest wrapperRequest=(MutableHttpServletRequest)request;
        if (WebUtils.isAdminAuth(wrapperRequest) || WebUtils.isEndUserAuth(wrapperRequest)){
            //Validate Token
            throw new InValidJwtTokenException("Jwt Token is not valid");
        }
        return true;
    }
}
