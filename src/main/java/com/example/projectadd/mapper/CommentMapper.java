package com.example.projectadd.mapper;

import DTO.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.stream.events.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "pk", target = "id")
    @Mapping(target = "user.id", source = "author")
    @Mapping(target = "user.image.path", source = "authorImage")
    @Mapping(target = "user.firstName", source = "authorFirstName")
    Comment toAdsComment(CommentDTO commentDTO);
    CommentDTO toCommentDto(Comment adsComment);

}