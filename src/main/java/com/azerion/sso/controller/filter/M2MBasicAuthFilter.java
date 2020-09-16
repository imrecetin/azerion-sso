package com.azerion.sso.controller.filter;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.util.WebUtils;
import com.azerion.sso.exception.InValidXAuthTypeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class M2MBasicAuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws AuthenticationException, IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        if (!WebUtils.isM2MAuth(request)){
            throw new InValidXAuthTypeException("M2M x-auth-type ile istek oluşturmalısınız");
        }
        String clientId = WebUtils.getClientIdFromHeader(mutableRequest);
        String clientSecret = WebUtils.getClientSecretFromHeader(mutableRequest);
        if (!StringUtils.isEmpty(clientId) && !StringUtils.isEmpty(clientSecret)){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(clientId, clientSecret);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(mutableRequest, servletResponse);
    }

}
