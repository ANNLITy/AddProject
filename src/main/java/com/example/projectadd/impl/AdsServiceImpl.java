package com.example.projectadd.impl;

import com.example.projectadd.model.Ads;
import com.example.projectadd.repository.AdsRepository;
import com.example.projectadd.service.AdsService;

public class AdsServiceImpl implements AdsService {
    private final AdsRepository repository;

    public AdsServiceImpl(AdsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Ads ads) {
        repository.save(ads);
    }

    @Override
    public void update(Ads ads) {
        repository.save(ads);
    }

    @Override
    public void delete(Ads ads) {
        repository.delete(ads);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Ads getById(int id) {
        return repository.findById(id).orElseThrow(Error::new);
    }
}