package com.example.projectadd.controller;

import com.example.projectadd.DTO.LoginReqDTO;
import com.example.projectadd.DTO.RegisterReqDTO;
import com.example.projectadd.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Аутентификация", description = "AuthController")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Operation(summary = "Авторизация пользователя", description = "login", tags={ "Авторизация" })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDTO req) {
        printLogInfo("login", "post", "/login");
        if (authService.login(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Регистрация пользователя", description = "register", tags={ "Регистрация" })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDTO req) {
        printLogInfo("register", "post", "/register");
        if (authService.register(req)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private void printLogInfo(String name, String requestMethod, String path) {
        logger.info("Вызван метод " + name + ", адрес "
                + requestMethod.toUpperCase() + " запроса " + path);
    }
}