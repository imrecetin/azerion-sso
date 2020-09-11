package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.WebUtils;
import com.azerion.sso.exception.InvalidM2MClientException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class M2MClientIDValidationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("M2MRequestParametersInterceptor Start");
        if (WebUtils.isM2MAuth(request)){
            String clientId=WebUtils.getClientIdFromHeader(request);
            if (!Arrays.asList("client1","client2").contains(clientId)){
                throw new InvalidM2MClientException("M2m için geçersiz clientId");
            }
        }
        return true;
    }
}
