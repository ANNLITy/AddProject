package com.example.projectadd.impl;

import com.example.projectadd.DTO.RegisterDTO;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.mapper.UserMapper;
import com.example.projectadd.service.AuthService;
import com.example.projectadd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        User user = userService.getUser(userName);
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean register(RegisterDTO registerReqDTO) {
        User user = userMapper.toEntity(registerReqDTO);
        return userService.createUser(user);
    }
}
