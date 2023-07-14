package com.example.projectadd.repository.mapper;

import com.example.projectadd.DTO.CommentDTO;
import com.example.projectadd.model.Comment;
import com.example.projectadd.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "user.id", source = "author")
    @Mapping(target = "user.image.id", source = "authorImage")
    @Mapping(target = "user.firstName", source = "authorFirstName")
    Comment toAdsComment(CommentDTO commentDTO);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.image", target = "authorImage", qualifiedByName = "imageMapper")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    CommentDTO toCommentDto(Comment adsComment);

    @Named("imageMapper")
    default String imageMapper(Image image) {
        if (image == null) {
            return null;
        }
        return "/users/image/" + image.getId();
    }

}