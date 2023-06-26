package com.example.projectadd.impl;

import com.example.projectadd.exception.ImageNotFoundException;
import com.example.projectadd.model.Image;
import com.example.projectadd.repository.ImageRepository;
import com.example.projectadd.service.ImageService;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.UUID;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadImage(MultipartFile imageFile) {
        Image image = new Image();
        try {
            String fileId = UUID.randomUUID().toString();
            image.setId(Long.valueOf(fileId));
            image.setBytes(imageFile.getBytes());
            return image;
        } catch (IOException e) {
            throw new RuntimeException("byte exception");
        }
    }

    @Override
    public byte[] loadImage(String filename) {
        Image image = imageRepository.findById(Integer.valueOf(filename)).orElseThrow(ImageNotFoundException::new);
        return image.getBytes();
    }

    @Override
    public Image getImageById(String id) {
        return imageRepository.findById(Integer.valueOf(id)).orElseThrow(
                () -> new NotFoundException("Image with id " + id + " not found!"));
    }

    @Override
    public void remove(Image image) {
        imageRepository.delete(image);
    }

}
