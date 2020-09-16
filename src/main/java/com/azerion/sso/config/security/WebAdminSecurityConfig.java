package com.azerion.sso.config.security;


import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import com.azerion.sso.config.security.provider.AdminAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
public class WebAdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${com.auth0.domain}")
    private String domain;

    @Value(value = "${com.auth0.clientId}")
    private String clientId;

    @Value(value = "${com.auth0.clientSecret}")
    private String clientSecret;

    @Value(value = "${com.auth0.issuer}")
    private String issuer;

    @Value(value = "${com.auth0.apiAudience}")
    private String apiAudience;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwkProvider jwkProvider = (new JwkProviderBuilder(issuer)).build();
        final AdminAuthenticationProvider adminAuthenticationProvider = new AdminAuthenticationProvider(jwkProvider, issuer, apiAudience);
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer,adminAuthenticationProvider)
                .configure(http)
                .antMatcher("/api/*/admin/**")
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/*/admin/todo/?").permitAll()
                .antMatchers("/api/*/admin/todo/**").authenticated();

    }

    public String getDomain() {
        return domain;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
