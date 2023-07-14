package com.example.projectadd.impl;

import com.example.projectadd.exception.ImageNotFoundException;
import com.example.projectadd.model.Image;
import com.example.projectadd.repository.ImageRepository;
import com.example.projectadd.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.UUID;


@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Метод загрузки изображения к объявлению
     */

    @Override
    public Image uploadImage(MultipartFile imageFile) {
        Image image = new Image();
        try {
            image.setBytes(imageFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Byte exception");
        }
        String fileId = UUID.randomUUID().toString();
        image.setId(fileId);
//        imageRepository.saveAndFlush(image);
        return image;
    }

    /**
     * Метод загрузки изображения к объявлению
     */
    @Override
    public byte[] loadImage(String filename) {
        Image image = imageRepository.findById(filename).orElseThrow(ImageNotFoundException::new);
        return image.getBytes();
    }

    /**
     * Метод получения изображения к объявлению по id
     */

    @Override
    public Image getImageById(String id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id " + id + " not found!"));
    }

    /**
     * Метод удаления изображения к объявлению
     */

    @Override
    public void remove(Image image) {
        imageRepository.delete(image);
    }

}
