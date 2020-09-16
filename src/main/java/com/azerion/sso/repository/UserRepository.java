package com.azerion.sso.repository;

import com.azerion.sso.config.security.oauth2.AuthProvider;
import com.azerion.sso.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository{

    private static List<User> users=new ArrayList<>();

    static{
        users.add(User.builder().email("ctn.imre@gmail.com").name("Çetin İmre").provider(AuthProvider.facebook).build());
    }

    public Optional<User> findByEmail(String email){
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();
    }

    public Boolean existsByEmail(String email){
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public User save(User user){
        users.add(user);
        return user;
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findAny();
    }
}
