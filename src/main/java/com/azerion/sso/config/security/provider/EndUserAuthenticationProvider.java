package com.azerion.sso.config.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EndUserAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        List<GrantedAuthority> totalGrantedAuthorities=new ArrayList<>(oauthToken.getAuthorities());
        totalGrantedAuthorities.add(new SimpleGrantedAuthority("TODO_ADD"));
        Authentication auth = new OAuth2AuthenticationToken(oauthToken.getPrincipal(), totalGrantedAuthorities,oauthToken.getAuthorizedClientRegistrationId());
        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
