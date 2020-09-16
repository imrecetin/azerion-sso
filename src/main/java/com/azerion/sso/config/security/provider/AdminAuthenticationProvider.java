package com.azerion.sso.config.security.provider;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.spring.security.api.JwtAuthenticationProvider;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import com.azerion.sso.config.security.TokenAuthentication;
import com.azerion.sso.exception.InValidJwtTokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;


public class AdminAuthenticationProvider extends JwtAuthenticationProvider {

    public AdminAuthenticationProvider(JwkProvider jwkProvider, String issuer, String audience) {
        super(jwkProvider, issuer, audience);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationJsonWebToken authenticate = (AuthenticationJsonWebToken)super.authenticate(authentication);
        final String jwtToken = authenticate.getToken();
        final DecodedJWT decodedJWT = JWT.decode(jwtToken);
        TokenAuthentication tokenAuthentication=new TokenAuthentication(decodedJWT);
        if (!tokenAuthentication.isAuthenticated()){
            throw new InValidJwtTokenException("Invalid Admin JWT Token");
        }
        final Map<String, Claim> claims = tokenAuthentication.getClaims();
        final Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
        //We can replace or transform some Claims or authorities with todo:delete or todo:add
        return authenticate;
    }
}
