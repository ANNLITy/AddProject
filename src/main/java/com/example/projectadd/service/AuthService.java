package com.example.projectadd.service;

import DTO.RegisterDTO;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDTO registerReqDTO);
}