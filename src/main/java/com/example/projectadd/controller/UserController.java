package com.example.projectadd.controller;

import DTO.NewPasswordDTO;
import DTO.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    @PostMapping("/set_password")
    @Operation(summary ="Установка пароля пароля" )
    public ResponseEntity<NewPasswordDTO> setPassword(@RequestBody NewPasswordDTO newPassword) {
        return ResponseEntity.ok().build();
    }



    @GetMapping("/me")
    @Operation(summary ="Получить информацию об авторизованном пользователе" )
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok().build();
    }



    @PatchMapping("/me")
    @Operation(summary ="Обновить информацию об авторизованном пользователе" )
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().build();
    }



    @PatchMapping("/me/image")
    @Operation(summary ="Обновить изображение авторизованного пользователя" )
    public ResponseEntity<UserDTO> updateUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}