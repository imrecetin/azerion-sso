package com.azerion.sso.controller.filter;


import com.azerion.sso.controller.MutableHttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ConvertToMutableHttpServletRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
        filterChain.doFilter(mutableRequest, servletResponse);
    }
}
