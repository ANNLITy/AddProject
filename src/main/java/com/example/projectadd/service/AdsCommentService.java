package com.example.projectadd.service;

import com.example.projectadd.model.AdsComment;

public interface AdsCommentService {
    void save(AdsComment adsComment);

    void update(AdsComment adsComment);

    void delete(AdsComment adsComment);
    void deleteById(int id);
    AdsComment getById(int id);
}
