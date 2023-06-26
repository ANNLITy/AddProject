package com.example.projectadd.service;

import com.example.projectadd.DTO.RegisterDTO;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDTO registerReqDTO);
}