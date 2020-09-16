package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.util.WebUtils;
import com.azerion.sso.exception.InValidXAuthTypeException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class M2MRequestParametersToHeaderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MutableHttpServletRequest wrapperRequest=(MutableHttpServletRequest)request;
        if (!WebUtils.isM2MAuth(wrapperRequest)){
            throw new InValidXAuthTypeException("M2M x-auth-type ile istek oluşturmalısınız");
        }
        final String clientId = WebUtils.getClientIdFromParameter(wrapperRequest);
        final String clientSecret = WebUtils.getClientSecretFromParameter(wrapperRequest);
        if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(clientSecret)){
            WebUtils.putClientIdAndSecretToHeader(wrapperRequest,clientId,clientSecret);
        }
        return true;
    }
}
