package com.example.projectadd.impl;

import com.example.projectadd.model.AdsComment;
import com.example.projectadd.repository.AdsCommentRepository;
import com.example.projectadd.service.AdsCommentService;

public class AdsCommentServiceImpl implements AdsCommentService {
    private final AdsCommentRepository repository;

    public AdsCommentServiceImpl(AdsCommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AdsComment adsComment) {
        repository.save(adsComment);
    }

    @Override
    public void update(AdsComment adsComment) {
        repository.save(adsComment);
    }

    @Override
    public void delete(AdsComment adsComment) {
        repository.delete(adsComment);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public AdsComment getById(int id) {
        return repository.findById(id).orElseThrow(Error::new);
    }
}

