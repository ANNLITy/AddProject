package com.example.projectadd.repository.mapper;

import com.example.projectadd.DTO.RegisterDTO;
import com.example.projectadd.DTO.UserDTO;
import com.example.projectadd.model.Image;
import com.example.projectadd.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "image", target = "image", qualifiedByName = "imageMapper")
    UserDTO toDto(User user);

    //Маперы для регистрации
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User toEntity(RegisterDTO dto);

    @Named("imageMapper")
    default String imageMapper(Image image) {
        if (image == null) {
            return null;
        }
        return "/users/image/" + image.getId();
    }
}
