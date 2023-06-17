package com.example.projectadd.impl;

import DTO.CommentDTO;
import DTO.CreateCommentDTO;
import DTO.ResponseWrapperCommentDTO;
import com.example.projectadd.admin.AdminUtils;
import com.example.projectadd.exception.CommentNotFoundException;
import com.example.projectadd.mapper.CommentMapper;
import com.example.projectadd.model.Ads;
import com.example.projectadd.model.Comment;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.AdsRepository;
import com.example.projectadd.repository.CommentRepository;
import com.example.projectadd.service.AdsService;
import com.example.projectadd.service.CommentService;
import com.example.projectadd.service.UserService;
import org.springframework.security.core.Authentication;
import org.webjars.NotFoundException;

import java.util.List;

import static com.example.projectadd.config.Const.*;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, AdsService adsService, CommentMapper commentMapper, UserService userService) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    @Override
    public ResponseWrapperCommentDTO getComments(Integer id) {
        List<CommentDTO> comments = commentRepository.findByAdsId(id).stream()
                .map(commentMapper::toCommentDto)
                .toList();
        return new ResponseWrapperCommentDTO(comments.size(), comments);
    }

    @Override
    public CommentDTO addComment(Integer id, CreateCommentDTO createCommentDTO, Authentication authentication) {
        Ads ads = adsRepository.findById(id).orElseThrow();
        User user = userService.checkUserByUsername(authentication.getName());

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setAds(ads);
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(createCommentDTO.getText());

        comment = (Comment) commentRepository.save((javax.xml.stream.events.Comment) comment);

        return commentMapper.toCommentDto((javax.xml.stream.events.Comment) comment);
    }

    @Override
    public CommentDTO updateComment(Integer adId, Integer commentId, CommentDTO commentDTO, Authentication authentication) {
        AdminUtils.checkPermissionToAdsComment((Comment) commentMapper.toAdsComment(commentDTO),
                userService.checkUserByUsername(authentication.getName()));
        if (commentRepository.findById(commentId).isPresent()) {
            if (commentRepository.findById(commentId).get().getId() !=adId){
                throw new NotFoundException(COMMENT_NOT_BELONG_AD);
            }

        } else {
            throw new CommentNotFoundException(COMMENT_NOT_FOUND);
        }
        Comment comment = (Comment) commentRepository.findById(commentId).orElseThrow();
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(commentDTO.getText());
        commentRepository.save((javax.xml.stream.events.Comment) comment);
        return commentMapper.toCommentDto((javax.xml.stream.events.Comment) comment);
    }


    @Override
    public boolean deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        Comment comment = (Comment) commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND.formatted(commentId)));
        if (comment.getAds().getId() != adId) {
            throw new NotFoundException(COMMENT_NOT_BELONG_AD);
        }
        AdminUtils.checkPermissionToAdsComment(comment, userService.checkUserByUsername(authentication.getName()));
        commentRepository.delete((javax.xml.stream.events.Comment) comment);
        return true;
    }
}
