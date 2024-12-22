package com.majesty.pet_care.service.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.repository.ReviewRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review, Long reviewerId, Long veterinarianId) {
       
        return null;
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
    public void updateReview(Review review, Long reviewId) {
        reviewRepository.findById(reviewId)
            .ifPresentOrElse(existingReview -> {
                existingReview.setStars(review.getStars());
                existingReview.setFeedback(review.getFeedback());
                reviewRepository.save(existingReview);
            }, () -> {
                throw new RessourceNotFoundException(FeedbackMessage.NOT_FOUND);
            });    
        
    }

    @Override
    public Page<Review> findAllReviewsByUserId(Long userId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findAllByUserId(userId, pageRequest);
    }

}
