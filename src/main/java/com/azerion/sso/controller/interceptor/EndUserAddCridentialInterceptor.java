package com.azerion.sso.controller.interceptor;

import com.azerion.sso.controller.MutableHttpServletRequest;
import com.azerion.sso.controller.WebUtils;
import com.azerion.sso.exception.InValidXAuthTypeException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EndUserAddCridentialInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MutableHttpServletRequest wrapperRequest=(MutableHttpServletRequest)request;
        if (!WebUtils.isEndUserAuth(wrapperRequest)){
            throw new InValidXAuthTypeException("EndUser x-auth-type ile istek oluşturmalısınız");
        }
        //JWT cridential'lara "todo:add" eklenecek
        return true;
    }
}
