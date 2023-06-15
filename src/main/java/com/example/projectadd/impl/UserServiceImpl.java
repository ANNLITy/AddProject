package com.example.projectadd.impl;

import DTO.NewPasswordDTO;
import DTO.UserDTO;
import com.example.projectadd.exception.UserNotFoundException;
import com.example.projectadd.mapper.UserMapper;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.UserRepository;
import com.example.projectadd.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public void setPassword(NewPasswordDTO newPasswordDto, String userName) {
        User user = checkUserByUsername(userName);
        user.setPassword(newPasswordDto.getNewPassword());
        userRepository.save(user);
    }

    @Override
    public UserDTO getUser(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            return userMapper.toDto(user);
        } else {
            return null;
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, String userName) {
        User user = checkUserByUsername(userName);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public User checkUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(toString());
        }
        return user;
    }
}
