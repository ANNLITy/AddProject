package com.example.projectadd.impl;

import com.example.projectadd.DTO.CommentDTO;
import com.example.projectadd.DTO.CreateCommentDTO;
import com.example.projectadd.DTO.ResponseWrapperCommentDTO;
import com.example.projectadd.config.Role;
import com.example.projectadd.exception.AdNotFoundException;
import com.example.projectadd.exception.CommentNotFoundException;
import com.example.projectadd.exception.NoAccessException;
import com.example.projectadd.repository.mapper.CommentMapper;
import com.example.projectadd.model.Ads;
import com.example.projectadd.model.Comment;
import com.example.projectadd.model.User;
import com.example.projectadd.repository.AdsRepository;
import com.example.projectadd.repository.CommentRepository;
import com.example.projectadd.service.CommentService;
import com.example.projectadd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.projectadd.config.Const.*;
@Slf4j
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, CommentMapper commentMapper, UserService userService) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    /**
     * Метод получения списка комментариев к объявлению
     */

    @Override
    public ResponseWrapperCommentDTO getComments(Integer id) {
        List<CommentDTO> comments = commentRepository.getCommentsByAds_Id(id).stream()
                .map(commentMapper::toCommentDto)
                .toList();
        return new ResponseWrapperCommentDTO(comments.size(), comments);
    }

    /**
     * Метод добавления комментария к объявлению
     */

    @Override
    public CommentDTO addComment(Integer id, CreateCommentDTO createCommentDTO, Authentication authentication) {
        if (createCommentDTO.getText().isBlank()) {
            throw new IllegalArgumentException("Нет текста комментария");
        }
        Ads ads = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        User user = userService.getUser(authentication.getName());

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setAds(ads);
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(createCommentDTO.getText());

        comment = commentRepository.save(comment);

        return commentMapper.toCommentDto(comment);
    }

    /**
     * Метод изменения комментария к объявлению
     */

    @Override
    public CommentDTO updateComment(Integer adId, Integer commentId, CommentDTO commentDTO, Authentication authentication) {
        if (checkCommentAccess(commentId, authentication)) {
            Comment comment = commentRepository.getById(commentId);
            comment.setCreatedAt(System.currentTimeMillis());
            comment.setText(commentDTO.getText());
            commentRepository.save(comment);
            return commentMapper.toCommentDto(comment);
        }
        throw new NoAccessException("Нет доступа к комментарию");
    }

    /**
     * Метод удаления комментария к объявлению
     */

    @Override
    public boolean deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND.formatted(commentId)));
        if (comment.getAds().getId() != adId) {
            throw new CommentNotFoundException(COMMENT_NOT_BELONG_AD);
        }
        if (checkCommentAccess(commentId, authentication)) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    private boolean checkCommentAccess(int commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (userService.getUser(authentication.getName()).getAuthorities().contains(Role.ADMIN)) {
            return true;
        }
        return comment.getUser().getEmail().equals(authentication.getName());
    }
}
