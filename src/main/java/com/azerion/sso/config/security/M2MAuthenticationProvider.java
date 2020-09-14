package com.azerion.sso.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class M2MAuthenticationProvider implements AuthenticationProvider {

    List<User> dummyClients = new ArrayList<>();

    public M2MAuthenticationProvider() {
        dummyClients.add(new User("client1", "pass1", Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT"))));
        dummyClients.add(new User("client2", "pass2", Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT"))));
    }

    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> authenticatedUser = dummyClients.stream().filter(
                user -> user.getUsername().equals(name) && user.getPassword().equals(password)).findFirst();

        if(!authenticatedUser.isPresent()){
            throw new BadCredentialsException("Client Name or Client Pass is not correct");
        }

        final Collection<GrantedAuthority> grantedAuthorities = authenticatedUser.get().getAuthorities();
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
