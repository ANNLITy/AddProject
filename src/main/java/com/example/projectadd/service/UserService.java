package com.example.projectadd.service;

import com.example.projectadd.DTO.NewPasswordDTO;
import com.example.projectadd.DTO.UserDTO;
import com.example.projectadd.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
//    UserDTO getAuthenticatedUser();

    void createUser(User user);
    boolean setPassword(NewPasswordDTO newPasswordDto, String userName);
    User getUserById(int id);
    User getUser(String userName);
    UserDTO getUser(Authentication authentication);
    UserDTO updateUser(UserDTO userDto, String userName);
    User checkUserByUsername(String username);
    UserDTO updateUserImage(MultipartFile image, Authentication authentication);
}

