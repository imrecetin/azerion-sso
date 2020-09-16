package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.util.WebUtils;
import com.azerion.sso.exception.NotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Component
public class AuthTypeHeaderParameterInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityContextHolderAwareRequestWrapper securityRequest=(SecurityContextHolderAwareRequestWrapper)request;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Principal userPrincipal = securityRequest.getUserPrincipal();
        if (!authentication.isAuthenticated()){
            throw new NotAuthenticatedException("Not Authenticated");
        }
        if (WebUtils.isM2MAuth(request)){
            final String clientId = WebUtils.getClientIdFromHeader(request);
            request.setAttribute("x-auth-id","M2M/"+clientId);
        }else if (WebUtils.isAdminAuth(request)){
            String auth0Id=authentication.getName();
            request.setAttribute("x-auth-id","Admin/"+auth0Id);
        }else if (WebUtils.isEndUserAuth(request)){
            String socialId=authentication.getName();
            request.setAttribute("x-auth-id","Social/"+socialId);
        }else{
            request.setAttribute("x-auth-id","free");
        }
        return true;
    }
}
