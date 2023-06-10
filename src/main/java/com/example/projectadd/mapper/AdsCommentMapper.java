package com.example.projectadd.mapper;

import DTO.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.stream.events.Comment;

@Mapper(componentModel= "spring")
public interface AdsCommentMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.image", target = "authorImage")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    Comment toAdsComment(CommentDTO commentDTO);
    CommentDTO toCommentDto(Comment adsComment);

}

