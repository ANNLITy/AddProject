package com.example.projectadd.controller;

import com.example.projectadd.DTO.NewPasswordDTO;
import com.example.projectadd.DTO.UserDTO;
import com.example.projectadd.model.User;
import com.example.projectadd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService= userService;
    }
    private void printLogInfo(String name, String requestMethod, String path) {
        logger.info("Вызван метод " + name + ", адрес "
                + requestMethod.toUpperCase() + " запроса " + path);
    }

    @PostMapping("/set_password")
    @Operation(summary ="Обновление  пароля" )
    public ResponseEntity<UserDTO> setPassword(@RequestBody NewPasswordDTO newPassword,  Authentication authentication) {
        if (userService.setPassword(newPassword, authentication.getName())) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Получить информацию об авторизованном пользователе")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @PostMapping("/me/image")
    @Operation(summary ="Обновить аватар авторизованного пользователя" )
    public ResponseEntity<Void> setPassword(@RequestBody MultipartFile image,  Authentication authentication) {
        try {
            userService.updateUserImage(image, authentication.getName());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            logger.error(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }


}