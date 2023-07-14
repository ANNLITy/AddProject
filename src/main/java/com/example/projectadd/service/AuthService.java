package com.example.projectadd.service;

import com.example.projectadd.DTO.RegisterReqDTO;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterReqDTO registerReqDTO);
}