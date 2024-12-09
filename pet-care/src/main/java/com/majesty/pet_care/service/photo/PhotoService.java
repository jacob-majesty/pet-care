package com.majesty.pet_care.service.photo;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.majesty.pet_care.model.Photo;

public class PhotoService implements IPhotoService {


    @Override
    public Photo savePhoto(MultipartFile photo, Long userId) {
        return null;
    }

    @Override
    public Optional<Photo> getPhotoById(Long id) {
            return null;
    }

    @Override
    public void deletePhoto(Long id) {}

    @Override
    public Photo updatePhoto(Long id, byte[] imageData) {
        return null;
    }

    @Override
    public byte[] getImageData(Long id) {
        return new byte[0];
    }

    

}
