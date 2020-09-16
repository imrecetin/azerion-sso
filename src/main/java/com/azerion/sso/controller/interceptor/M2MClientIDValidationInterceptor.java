package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.util.WebUtils;
import com.azerion.sso.exception.InValidXAuthTypeException;
import com.azerion.sso.exception.InValidM2MClientException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class M2MClientIDValidationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MutableHttpServletRequest wrapperRequest=(MutableHttpServletRequest)request;
        if (!WebUtils.isM2MAuth(wrapperRequest)){
            throw new InValidXAuthTypeException("M2M x-auth-type ile istek oluşturmalısınız");
        }

        String clientId=WebUtils.getClientIdFromHeader(wrapperRequest);
        final Optional<WebUtils.M2M_CLIENT> m2mClient = WebUtils.M2M_CLIENT.of(clientId);
        if (!m2mClient.isPresent()){
            throw new InValidM2MClientException("M2M için geçersiz clientId : "+clientId);
        }

        return true;
    }
}
