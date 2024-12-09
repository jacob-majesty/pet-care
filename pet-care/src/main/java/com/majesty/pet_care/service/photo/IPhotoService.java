package com.majesty.pet_care.service.photo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.majesty.pet_care.model.Photo;

public interface IPhotoService {

    Photo savePhoto(MultipartFile photo, Long userId) throws IOException, SQLException;
    Optional<Photo> getPhotoById(Long id);
    void deletePhoto(Long id);
    Photo updatePhoto(Long id, byte[] imageData);
    byte[] getImageData(Long id);

}
