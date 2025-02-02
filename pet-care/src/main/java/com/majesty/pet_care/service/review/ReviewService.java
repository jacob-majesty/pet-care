package com.majesty.pet_care.service.review;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.majesty.pet_care.enums.AppointmentStatus;
import com.majesty.pet_care.exception.AlreadyExistsException;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.AppointmentRepository;
import com.majesty.pet_care.repository.ReviewRepository;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.ReviewUpdateRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Review saveReview(Review review, Long reviewerId, Long veterinarianId) {
        if (veterinarianId.equals(reviewerId)) {
            throw new IllegalArgumentException(FeedbackMessage.CANNOT_REVIEW);
        }

        Optional<Review> existingReview = reviewRepository.findByVeterinarianIdAndPatientId(veterinarianId, reviewerId);
        if (existingReview.isPresent()) {
            throw new AlreadyExistsException(FeedbackMessage.ALREADY_REVIEWED);
        }
        boolean hadCompletedAppointments = appointmentRepository
                .existsByVeterinarianIdAndPatientIdAndStatus(veterinarianId, reviewerId, AppointmentStatus.COMPLETED);
        if (!hadCompletedAppointments) {
            throw new IllegalStateException(FeedbackMessage.NOT_ALLOWED);
        }

        User veterinarian = userRepository.findById(veterinarianId)
                .orElseThrow(() -> new ResourceNotFoundException(FeedbackMessage.VET_OR_PATIENT_NOT_FOUND));

        User patient = userRepository.findById(reviewerId)
                .orElseThrow(() -> new ResourceNotFoundException(FeedbackMessage.VET_OR_PATIENT_NOT_FOUND));

        review.setVeterinarian(veterinarian);
        review.setPatient(patient);
        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    @Override
    public double getAverageRatingForVet(Long veterinarianId) {
        List<Review> reviews = reviewRepository.findByVeterinarianId(veterinarianId);
        return reviews.isEmpty() ? 0
                : reviews.stream()
                        .mapToInt(Review::getStars)
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
                }).orElseThrow(() -> new ResourceNotFoundException(FeedbackMessage.RESOURCE_NOT_FOUND));
    }

    @Override
    public Page<Review> findAllReviewsByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findAllByUserId(userId, pageRequest);
    }

    @Override
    public void deleteReview(Long reviewerId) {
        reviewRepository.findById(reviewerId)
                .ifPresentOrElse(Review::removeRelationShip, () -> {
                    throw new ResourceNotFoundException(FeedbackMessage.RESOURCE_NOT_FOUND);
                });
        reviewRepository.deleteById(reviewerId);
    }

}
