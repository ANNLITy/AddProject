package com.example.projectadd.controller;

import com.example.projectadd.DTO.NewPasswordDTO;
import com.example.projectadd.DTO.UserDTO;
import com.example.projectadd.service.ImageService;
import com.example.projectadd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи", description = "UserController")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final ImageService imageService;
    public UserController(UserService userService, ImageService imageService) {
        this.userService= userService;
        this.imageService = imageService;
    }
    private void printLogInfo(String name, String requestMethod, String path) {
        logger.info("Вызван метод " + name + ", адрес "
                + requestMethod.toUpperCase() + " запроса " + path);
    }

    @PostMapping("/set_password")
    @Operation(summary ="Обновление  пароля")
    public ResponseEntity<UserDTO> setPassword(@RequestBody NewPasswordDTO newPassword,
                                               Authentication authentication) {
        printLogInfo("setPassword", "post", "/set_password");
        if (userService.setPassword(newPassword, authentication.getName())) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Получить информацию об авторизованном пользователе")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        printLogInfo("getUser", "get", "/me");
        UserDTO user = userService.getUser(authentication);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/me")
    @Operation(summary ="Обновить информацию об авторизованном пользователе" )
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, Authentication authentication) {
        printLogInfo("updateUser", "patch", "/me");
        UserDTO user = userService.updateUser(userDTO, authentication.getName());
        if (user != null) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary ="Обновить аватар авторизованного пользователя")
    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile image,
                                             Authentication authentication) {
        printLogInfo("updateUserImage", "patch", "/me/image");
        userService.updateUserImage(image, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить картинку объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.loadImage(id));
    }


}