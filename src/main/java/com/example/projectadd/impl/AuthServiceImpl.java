package com.example.projectadd.impl;

import com.example.projectadd.DTO.RegisterReqDTO;
import com.example.projectadd.admin.UserDetails;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.UserRepository;
import com.example.projectadd.repository.mapper.UserMapper;
import com.example.projectadd.service.AuthService;
import com.example.projectadd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserService userService, UserRepository userRepository, UserDetailsServiceImpl userDetailsServiceImpl, UserMapper userMapper, PasswordEncoder encoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public boolean login(String userName, String password) {
//        User user = userService.getUser(userName);
//        return encoder.matches(password, user.getPassword());
        try {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
            String encryptedPassword = userDetails.getPassword();
            if (!encoder.matches(password, encryptedPassword)) {
                throw new BadCredentialsException("Wrong password!");
            }
            return true;
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException(String.format("User \"%s\" does not exist!", userName));
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Bad credentials!");
        }
    }

    @Override
    public boolean register(RegisterReqDTO registerReqDTO) {
//        User user = userMapper.toModel(registerReqDTO);
//        return userService.createUser(user);
        User user = userMapper.toModel(registerReqDTO);
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new RuntimeException("User already exist");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
