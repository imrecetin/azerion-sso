package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class M2MRequestParametersInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("M2MRequestParametersInterceptor Start");
        if (WebUtils.isM2MAuth(request)){
            final String clientId = WebUtils.getClientIdFromParameter(request);
            final String clientSecret = WebUtils.getClientSecretFromParameter(request);
            if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(clientSecret)){
                WebUtils.putClientIdAndSecretToHeader(request,clientId,clientSecret);
            }
        }
        return true;
    }
}
