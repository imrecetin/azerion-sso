package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.util.WebUtils;
import com.azerion.sso.exception.InValidXAuthTypeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Component
public class M2MAddCridentialInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MutableHttpServletRequest wrapperRequest=(MutableHttpServletRequest)request;
        if (!WebUtils.isM2MAuth(wrapperRequest)){
            throw new InValidXAuthTypeException("M2M x-auth-type ile istek oluşturmalısınız");
        }

        String clientId=WebUtils.getClientIdFromHeader(wrapperRequest);
        if (WebUtils.M2M_CLIENT.clientId1.clientUserName().equals(clientId)){
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        }

        return true;
    }
}
