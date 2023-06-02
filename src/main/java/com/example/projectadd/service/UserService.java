package com.example.projectadd.service;

import com.example.projectadd.model.User;

public interface UserService {
    void save(User user);

    void update(User user);

    void delete(User user);
    void deleteById(int id);
    User getById(int id);
}