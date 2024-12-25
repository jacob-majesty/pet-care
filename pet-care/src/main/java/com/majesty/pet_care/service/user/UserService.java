package com.majesty.pet_care.service.user;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.majesty.pet_care.dto.AppointmentDto;
import com.majesty.pet_care.dto.EntityConverter;
import com.majesty.pet_care.dto.ReviewDto;
import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.factory.UserFactory;
import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.request.UserUpdateRequest;
import com.majesty.pet_care.service.appointment.AppointmentService;
import com.majesty.pet_care.service.pet.IPetService;
import com.majesty.pet_care.service.photo.PhotoService;
import com.majesty.pet_care.service.review.ReviewService;
import com.majesty.pet_care.dto.ReviewDto;
import com.majesty.pet_care.dto.UserDto;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final EntityConverter<User, UserDto> entityConverter;
    private final AppointmentService appointmentService;
    private final IPetService petService;
    private final PhotoService photoService;
    private final ReviewService reviewService;
    private final EntityConverter<User, UserDto> userEntityConverter;

    @Override
    public User register(@RequestBody RegistrationRequest request) {  
        return userFactory.createUser(request);   
    }

    @Override
    public User update(Long UserId, UserUpdateRequest request) {
        User user = findById(UserId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber()); 
        user.setSpecialization(request.getSpecialization());
        return userRepository.save(user);
    }
    
    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
            .ifPresentOrElse(userRepository::delete, () ->{ 
                throw new ResourceNotFoundException("User not found");});

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(user -> entityConverter.mapEntityToDto(user, UserDto.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public UserDto getUserWithDetails(Long userId) throws SQLException {

        User user = findById(userId);

        UserDto userDto = entityConverter.mapEntityToDto(user, UserDto.class);

        setUserAppointments(userDto);

        setUserPhoto(userDto, user);

        setUserReviews(userDto, userId);

        return userDto;
    }



    private void setUserAppointments(UserDto  userDto) {
        List<AppointmentDto> appointments = appointmentService.getUserAppointments(userDto.getId());
        userDto.setAppointments(appointments);
    }

    private void setUserPhoto(UserDto userDto, User user) throws SQLException {
        if (user.getPhoto() != null) {
            userDto.setPhotoId(user.getPhoto().getId());
            userDto.setPhoto(photoService.getImageData(user.getPhoto().getId()));
        }     
    }

    private void setUserReviews(UserDto userDto, Long userId) throws SQLException {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, 0, Integer.MAX_VALUE);
        List<ReviewDto> reviewDto = reviewPage.getContent()
                .stream()
                .map(this::mapReviewToDto).toList();
         if(!reviewDto.isEmpty()) {
             double averageRating = reviewService.getAverageRatingForVet(userId);
         }
         userDto.setReviews(reviewDto);

    }

    private ReviewDto mapReviewToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setStars(review.getStars());
        reviewDto.setFeedback(review.getFeedback());
        mapVeterinarianInfo(reviewDto, review);
        mapPatientInfo(reviewDto, review);
        return reviewDto;
    }

    private void mapVeterinarianInfo(ReviewDto reviewDto, Review review) {
        if (review.getVeterinarian() != null) {
            reviewDto.setVeterinarianId(review.getVeterinarian().getId());
            reviewDto.setVeterinarianName(review.getVeterinarian().getFirstName()+ " " + review.getVeterinarian().getLastName());
            
            setVeterinarianPhoto(reviewDto, review);
        }
    }

    private void mapPatientInfo(ReviewDto reviewDto, Review review) {
        if (review.getPatient() != null) {
            reviewDto.setPatientId(review.getPatient().getId());
            reviewDto.setPatientName(review.getPatient().getFirstName()+ " " + review.getPatient().getLastName());
            
            setReviewerPhoto(reviewDto, review);
        }
    }

    private void setReviewerPhoto(ReviewDto reviewDto, Review review) {
        if(review.getPatient().getPhoto() != null) {
            try {
                reviewDto.setPatientImage(photoService.getImageData(review.getPatient().getPhoto().getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }else {
            reviewDto.setPatientImage(null);
        }
    }

    private void setVeterinarianPhoto(ReviewDto reviewDto, Review review) {
        if(review.getVeterinarian().getPhoto() != null) {
            try {
                reviewDto.setVeterinarianImage(photoService.getImageData(review.getVeterinarian().getPhoto().getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }else {
            reviewDto.setVeterinarianImage(null);
        }
    }




}