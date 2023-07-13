package com.example.projectadd.admin;

import com.example.projectadd.model.User;

public class UserDetails extends User{
    private final int id;

    public UserDetails(User user) {
        super(user.getEmail(), user.getPassword(), user.getRole());
        this.id = user.getId();
    }

//    @Override
//    public void eraseCredentials() {
//    }

    public int getId() {
        return id;
    }
}