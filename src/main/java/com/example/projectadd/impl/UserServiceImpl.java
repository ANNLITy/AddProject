package com.example.projectadd.impl;

import com.example.projectadd.DTO.NewPasswordDTO;
import com.example.projectadd.DTO.UserDTO;
import com.example.projectadd.exception.UserNotFoundException;
import com.example.projectadd.model.Image;
import com.example.projectadd.repository.mapper.UserMapper;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.UserRepository;
import com.example.projectadd.service.ImageService;
import com.example.projectadd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder, ImageService imageService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.imageService = imageService;
    }

    /**
     * Метод создания аккаунта пользователя
     */

    @Override
    public void createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exist");
        }
        userRepository.save(user);
    }

    /**
     * Метод создания и сохранения пароля пользователя
     */
    @Override
    public boolean setPassword(NewPasswordDTO newPasswordDto, String userName) {
        User user = checkUserByUsername(userName);
        if (user != null && encoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            String password = newPasswordDto.getNewPassword();
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    /**
     * Метод поиска пользователя по id
     */

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    /**
     * Метод поиска пользователя по UserName
     */
    @Override
    public User getUser(String userName) {
        return userRepository.findByEmail(userName);
    }

    /**
     * Метод поиска пользователя по логину
     */
    @Override
    public UserDTO getUser(Authentication authentication) {
        User user = getUser(authentication.getName());
        return userMapper.toDto(user);
    }

    /**
     * Метод обновления информации о пользователе
     */
    @Override
    public UserDTO updateUser(UserDTO userDto, String userName) {
        User user = checkUserByUsername(userName);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * Метод поиска пользователя по User Name
     */
    @Override
    public User checkUserByUsername(String username) {
        User user = getUser(username);
        if (user == null) {
            throw new UserNotFoundException(toString());
        }
        return user;
    }

    /**
     * Метод обновления изображения в аккаунте пользователя
     */

    @Override
    public UserDTO updateUserImage(MultipartFile image, Authentication authentication) {
        User user = checkUserByUsername(authentication.getName());
        Image userImage = user.getImage();
        if (userImage != null) {
            imageService.remove(userImage);
        }
        user.setImage(imageService.uploadImage(image));
        return userMapper.toDto(userRepository.save(user));
    }
}

