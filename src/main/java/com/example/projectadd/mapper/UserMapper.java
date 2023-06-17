package com.example.projectadd.mapper;

import DTO.RegisterDTO;
import DTO.UserDTO;
import com.example.projectadd.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "image", target = "image.path")
    User toUser(UserDTO userDTO);
    @Mapping(source = "image.path", target = "image")
    UserDTO toDto(User user);
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User toEntity(RegisterDTO dto);
}
