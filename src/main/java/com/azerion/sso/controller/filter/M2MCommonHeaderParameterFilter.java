package com.azerion.sso.controller.filter;

import com.azerion.sso.controller.util.WebUtils;
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
        if (!WebUtils.isM2MAuth((HttpServletRequest)servletRequest)){
            throw new InValidXAuthTypeException("M2M x-auth-type ile istek oluşturmalısınız");
        }
        String clientId = WebUtils.getClientIdFromParameter(servletRequest);
        String clientSecret = WebUtils.getClientSecretFromParameter(servletRequest);
        if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(clientSecret)){
            WebUtils.putClientIdAndSecretToAttribute(servletRequest,clientId,clientSecret);
        }
        clientId = WebUtils.getClientIdFromHeader((HttpServletRequest)servletRequest);
        clientSecret = WebUtils.getClientSecretFromHeader((HttpServletRequest)servletRequest);
        final Optional<WebUtils.M2M_CLIENT> m2mClient = WebUtils.M2M_CLIENT.of(clientId);
        if (!m2mClient.isPresent()){
            throw new InValidM2MClientException("M2M için geçersiz clientId : "+clientId);
        }
        if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(clientSecret)){
            WebUtils.putClientIdAndSecretToAttributeAsHttpBasic(servletRequest,clientId,clientSecret);
            WebUtils.putClientIdAndSecretToAttribute(servletRequest,clientId,clientSecret);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
