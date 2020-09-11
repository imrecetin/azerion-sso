package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthTypeHeaderParameterInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MutableHttpServletRequest wrapperRequest=(MutableHttpServletRequest)request;
        if (WebUtils.isM2MAuth(wrapperRequest)){
            final String clientId = WebUtils.getClientIdFromHeader(wrapperRequest);
            wrapperRequest.putHeader("x-auth-id","M2M/"+clientId);
        }else if (WebUtils.isAdminAuth(wrapperRequest)){
            String auth0Id="";
            wrapperRequest.putHeader("x-auth-id","Admin/"+auth0Id);
        }else if (WebUtils.isEndUserAuth(wrapperRequest)){
            String socialId="";
            wrapperRequest.putHeader("x-auth-id","Social/"+socialId);
        }else{
            wrapperRequest.putHeader("x-auth-id","free");
        }
        return true;
    }
}
