package com.example.projectadd.admin;

import com.example.projectadd.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserDetails extends org.springframework.security.core.userdetails.User{
    private final int id;

    public UserDetails(User user) {
        super(user.getEmail(), user.getPassword(), List.of((GrantedAuthority) user.getRole()));
        this.id = user.getId();
    }

    @Override
    public void eraseCredentials() {
    }

    public int getId() {
        return id;
    }
}