package com.example.projectadd.service;

import com.example.projectadd.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image uploadImage(MultipartFile imageFile);

    byte[] loadImage(String id);

    Image getImageById(String id);

    void remove(Image image);
}
