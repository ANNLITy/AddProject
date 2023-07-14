package com.example.projectadd.service;

import com.example.projectadd.DTO.CommentDTO;
import com.example.projectadd.DTO.CreateCommentDTO;
import com.example.projectadd.DTO.ResponseWrapperCommentDTO;
import org.springframework.security.core.Authentication;

public interface CommentService {


    ResponseWrapperCommentDTO getComments(Integer id);

    CommentDTO addComment(Integer id, CreateCommentDTO createCommentDTO, Authentication authentication);

    CommentDTO updateComment(Integer adId, Integer commentId, CommentDTO commentDTO, Authentication authentication);

    boolean deleteComment(Integer adId, Integer commentId, Authentication authentication);
}
