package com.majesty.pet_care.service.photo;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Photo;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.PhotoRepository;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoService implements IPhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;


    @Override
    public Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException {

        Optional<User> theUser = userRepository.findById(userId);
        Photo photo = new Photo();
        if (file != null && !file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            photo.setImage(photoBlob);
            photo.setFileType(file.getContentType());
        }

        Photo savedPhoto = photoRepository.save(photo);
        theUser.ifPresent(user -> {user.setPhoto(savedPhoto);});
        userRepository.save(theUser.get());
        return savedPhoto;
    }

    @Override
    public Optional<Photo> getPhotoById(Long id) {
            return photoRepository.findById(id);
    }

    @Override
    public void deletePhoto(Long id) {
        photoRepository.findById(id)
            .ifPresentOrElse(photoRepository::delete, () -> {
                throw new RessourceNotFoundException(FeedbackMessage.NOT_FOUND);});
    }

    @Override
    public Photo updatePhoto(Long id, byte[] imageData) {
        return null;
    }

    @Override
    public byte[] getImageData(Long id) {
        return new byte[0];
    }

    

}
