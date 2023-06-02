package com.example.projectadd.service;

import com.example.projectadd.model.Image;

public interface ImageService {
    void save(Image image);

    void update(Image image);

    void delete(Image image);
    void deleteById(int id);
    Image getById(int id);
}