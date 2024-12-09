package com.majesty.pet_care.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.majesty.pet_care.model.Photo;

public interface IImageService {

    Photo savePhoto(MultipartFile photo);
    Optional<Photo> getPhoto(Long id);
    void deletePhoto(Long id);

    Photo updatePhoto(Long id, byte[] imageData);
    byte[] getImage(Long id);

}
