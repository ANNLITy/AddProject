package com.example.projectadd.controller;

import DTO.NewPasswordDTO;
import DTO.UserDTO;
import com.example.projectadd.model.User;
import com.example.projectadd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService= userService;
    }

    @PostMapping("/set_password")
    @Operation(summary ="Установка  пароля" )
    public ResponseEntity<UserDTO> setPassword(@RequestBody NewPasswordDTO newPassword,  Authentication authentication) {
        User user = userService.checkUserByUsername(authentication.getName());
        if (user != null) {
            userService.setPassword(newPassword, authentication.getName());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



    @GetMapping("/info")
    @Operation(summary ="Получить информацию об авторизованном пользователе" )
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        UserDTO user = userService.getUser(authentication.getName());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



    @PatchMapping("/info")
    @Operation(summary ="Обновить информацию об авторизованном пользователе" )
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, Authentication authentication) {
        UserDTO user = userService.updateUser(userDTO, authentication.getName());
        if (user != null) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



    @PatchMapping("/info/image")
    @Operation(summary ="Обновить изображение авторизованного пользователя" )
    public ResponseEntity<UserDTO> updateUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}