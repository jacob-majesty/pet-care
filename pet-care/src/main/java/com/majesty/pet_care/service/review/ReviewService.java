package com.majesty.pet_care.service.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.majesty.pet_care.enums.AppointmentStatus;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.AppointmentRepository;
import com.majesty.pet_care.repository.ReviewRepository;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.ReviewUpdateRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Override
    public Review saveReview(Review review, Long reviewerId, Long veterinarianId) {

        // 1. Check if the reviewer is same as the doctor being reviewed
        if (reviewerId.equals(veterinarianId)) {
            throw new IllegalArgumentException(FeedbackMessage.CANNOT_REVIEW);
        }

        // 2. Check if the reviewer has previously submitted a review for this doctor.
        Optional<Review> existingReview = reviewRepository
                .findByReviewerIdAndVeterinarianId(reviewerId, veterinarianId);
        if (existingReview.isPresent()) {
            throw new IllegalArgumentException(FeedbackMessage.ALREADY_REVIEWED);
        }

        //3.Check if the reviewer has gotten a completed appointment with this doctor.
        boolean hadCompletedAppointment = appointmentRepository.existsByPatientIdAndVeterinarianIdAndStatus(reviewerId, veterinarianId, AppointmentStatus.COMPLETED);
        if (!hadCompletedAppointment) {
            throw new IllegalStateException(FeedbackMessage.NOT_ALLOWED);
        }

        //4 Get the veterinarian  from the database
        User veterinarian = userRepository.findById(veterinarianId)
            .orElseThrow(() -> new ResourceNotFoundException(FeedbackMessage.VET_OR_PATIENT_NOT_FOUND));

        //4 Get the patient from the database
        User patient = userRepository.findById(reviewerId)
            .orElseThrow(()->new ResourceNotFoundException(FeedbackMessage.VET_OR_PATIENT_NOT_FOUND));

        //5. Set both to the review
        review.setVeterinarian(veterinarian);
        review.setPatient(patient);

        //6. Save the review.
       return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public double getAverageRatingForVet(Long veterinarianId) {
        List<Review> reviews = reviewRepository.findByVeterinarianId(veterinarianId);
       
        return reviews.isEmpty() ? 0 : reviews.stream()
                .mapToInt(review -> review.stars)
                .average()
                .orElse(0.0);
    }

    @Override
    public Review updateReview(Long reviewerId, ReviewUpdateRequest review) {
        return reviewRepository.findById(reviewerId)
            .map(existingReview -> {
                existingReview.setStars(review.getStars());
                existingReview.setFeedback(review.getFeedback());
                return reviewRepository.save(existingReview);
            }).orElseThrow( () -> {
                throw new ResourceNotFoundException(FeedbackMessage.NOT_FOUND);
            });    
        
    }

    @Override
    public Page<Review> findAllReviewsByUserId(Long userId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findAllByUserId(userId, pageRequest);
    }

    @Override
    public void deleteReview(Long reviewerId) {
        reviewRepository.findById(reviewerId)
                .ifPresentOrElse(Review::removeRelationShip, ()->{
                   throw new ResourceNotFoundException(FeedbackMessage.RESOURCE_NOT_FOUND);
                });
        reviewRepository.deleteById(reviewerId);
    }

}

    


