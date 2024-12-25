package com.majesty.pet_care.service.photo;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.web.multipart.MultipartFile;

import com.majesty.pet_care.model.Photo;

public interface IPhotoService {

    Photo savePhoto(MultipartFile photo, Long userId) throws IOException, SQLException;
    Photo getPhotoById(Long id);
    void deletePhoto(Long id, Long userId) throws SQLException;
    Photo updatePhoto(Long id, MultipartFile file) throws SQLException, IOException;
    byte[] getImageData(Long id) throws SQLException;

}
