package com.azerion.sso.controller.filter;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.WebUtils;
import com.azerion.sso.exception.InValidM2MClientException;
import com.azerion.sso.exception.InValidXAuthTypeException;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class M2MCommonHeaderParameterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        if (!WebUtils.isM2MAuth(mutableRequest)){
            throw new InValidXAuthTypeException("M2M x-auth-type ile istek oluşturmalısınız");
        }
        String clientId = WebUtils.getClientIdFromParameter(mutableRequest);
        String clientSecret = WebUtils.getClientSecretFromParameter(mutableRequest);
        if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(mutableRequest)){
            WebUtils.putClientIdAndSecretToHeader(mutableRequest,clientId,clientSecret);
            WebUtils.putClientIdAndSecretToHeaderAsHttpBasic(mutableRequest,clientId,clientSecret);
        }
        clientId = WebUtils.getClientIdFromHeader(mutableRequest);
        clientSecret = WebUtils.getClientSecretFromHeader(mutableRequest);
        if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(clientSecret)){
            final Optional<WebUtils.M2M_CLIENT> m2mClient = WebUtils.M2M_CLIENT.of(clientId);
            if (!m2mClient.isPresent()){
                throw new InValidM2MClientException("M2M için geçersiz clientId : "+clientId);
            }
            WebUtils.putClientIdAndSecretToHeaderAsHttpBasic(mutableRequest,clientId,clientSecret);
        }
        filterChain.doFilter(mutableRequest, servletResponse);
    }
}
