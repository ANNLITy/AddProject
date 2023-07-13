package com.example.projectadd.impl;

import com.example.projectadd.DTO.RegisterDTO;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.mapper.UserMapper;
import com.example.projectadd.service.AuthService;
import com.example.projectadd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserService userService, UserMapper userMapper, PasswordEncoder encoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public boolean login(String userName, String password) {
        User user = userService.getUser(userName);
        return encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean register(RegisterDTO registerReqDTO) {
        User user = userMapper.toEntity(registerReqDTO);
        return userService.createUser(user);
    }
}
