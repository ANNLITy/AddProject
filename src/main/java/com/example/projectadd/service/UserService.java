package com.example.projectadd.service;

import DTO.NewPasswordDTO;
import DTO.UserDTO;
import com.example.projectadd.model.User;


public interface UserService {
    void setPassword(NewPasswordDTO newPasswordDto, String userName);
    UserDTO getUser(String userName);
    UserDTO updateUser(UserDTO userDto, String userName);
    User checkUserByUsername(String username);

}

