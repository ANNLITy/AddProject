package com.example.projectadd.controller;

import DTO.LoginDTO;
import DTO.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class RegistrController {
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO req) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO req) {
        return ResponseEntity.ok().build();
    }
}