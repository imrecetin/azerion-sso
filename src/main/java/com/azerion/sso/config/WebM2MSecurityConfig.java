package com.azerion.sso.config;

import com.azerion.sso.config.security.M2MAuthenticationProvider;
import com.azerion.sso.controller.filter.M2MBasicAuthFilter;
import com.azerion.sso.controller.filter.M2MCommonHeaderParameterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.ConcurrentSessionFilter;


@Configuration
@Order(3)
public class WebM2MSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    M2MAuthenticationProvider m2mAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(m2mAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         http.antMatcher("/api/*/m2m/**")
        .cors().and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET,"/api/*/m2m/todo/*").permitAll()
        .antMatchers("/api/*/m2m/todo/**").authenticated();

        http.addFilterAfter(new M2MBasicAuthFilter(), ConcurrentSessionFilter.class);
        http.addFilterBefore(new M2MCommonHeaderParameterFilter(), M2MBasicAuthFilter.class);

    }

}
