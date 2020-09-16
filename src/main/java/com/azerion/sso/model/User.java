package com.azerion.sso.model;

import com.azerion.sso.config.security.oauth2.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {

    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private String password;
    private AuthProvider provider;
    private String providerId;
}
