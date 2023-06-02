package com.example.projectadd.service;

import com.example.projectadd.model.Ads;

public interface AdsService {
    void save(Ads ads);

    void update(Ads ads);

    void delete(Ads ads);
    void deleteById(int id);
    Ads getById(int id);

}