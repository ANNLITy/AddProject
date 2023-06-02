package com.example.projectadd.impl;

import com.example.projectadd.model.Image;
import com.example.projectadd.repository.ImageRepository;
import com.example.projectadd.service.ImageService;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    public ImageServiceImpl(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Image image) {
        repository.save(image);
    }

    @Override
    public void update(Image image) {
        repository.save(image);
    }

    @Override
    public void delete(Image image) {
        repository.delete(image);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Image getById(int id) {
        return repository.findById(id).orElseThrow(Error::new);
    }
}
