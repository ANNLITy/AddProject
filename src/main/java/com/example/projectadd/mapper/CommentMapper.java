package com.example.projectadd.mapper;

import com.example.projectadd.DTO.CommentDTO;
import com.example.projectadd.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "pk", target = "id")
    @Mapping(target = "user.id", source = "author")
    @Mapping(target = "user.image.path", source = "authorImage")
    @Mapping(target = "user.firstName", source = "authorFirstName")
    Comment toAdsComment(CommentDTO commentDTO);
    CommentDTO toCommentDto(Comment adsComment);

}