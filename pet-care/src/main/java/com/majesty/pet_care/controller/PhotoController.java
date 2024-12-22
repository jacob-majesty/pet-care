package com.majesty.pet_care.controller;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.majesty.pet_care.exception.ResourceNotFoundException;

import com.majesty.pet_care.model.Photo;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.photo.IPhotoService;
import com.majesty.pet_care.utils.UrlMapping;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(UrlMapping.PHOTOS)
@RequiredArgsConstructor
public class PhotoController {

    private final IPhotoService photoService;

    @PostMapping(UrlMapping.UPLOAD_PHOTO)
    public ResponseEntity<ApiResponse> uploadPhoto(
            @RequestParam MultipartFile file,
            @RequestParam Long userId) throws SQLException, IOException {
        try {
            Photo photo = photoService.savePhoto(file, userId);
            return ResponseEntity.status(Response.SC_CREATED).body(new ApiResponse(FeedbackMessage.CREATE_SUCCESS, photo.getId()));
        } catch (IOException | SQLException e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_PHOTO_BY_ID)
    public ResponseEntity<ApiResponse> getPhotoById(@PathVariable Long photoId) {
        try {
            Photo photo = photoService.getPhotoById(photoId);
            if (photo != null) {
                byte[] photoBytes = photoService.getImageData(photo.getId());
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.FOUND, photoBytes));
            }
        } catch (ResourceNotFoundException | SQLException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
        }
        return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
    }      

    @DeleteMapping(UrlMapping.DELETE_PHOTO)
    public ResponseEntity<ApiResponse> deletePhoto(@PathVariable Long photoId, @PathVariable Long userId) throws IOException, SQLException {
        try {
            Photo photo = photoService.getPhotoById(photoId);
            if (photo != null) {
                photoService.deletePhoto(photo.getId(), userId);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.DELETE_SUCCESS, photo.getId()));
            }
        } catch (ResourceNotFoundException | SQLException e) {
        return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
        }
        return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
    }


    @PutMapping(UrlMapping.UPDATE_PHOTO)
    public ResponseEntity<ApiResponse> updatePhoto(@PathVariable Long photoId, @RequestBody MultipartFile file) throws IOException, SQLException {
        try {
            Photo photo = photoService.getPhotoById(photoId);
            if (photo != null) {
                Photo updatedPhoto = photoService.updatePhoto(photo.getId(), file);
                return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.UPDATE_SUCCESS, updatedPhoto.getId()));
            }
        } catch (ResourceNotFoundException | SQLException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
        }
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
        }
}