package com.majesty.pet_care.service.review;

import org.springframework.data.domain.Page;

import com.majesty.pet_care.model.Review;

public interface IReviewService {
    Review saveReview(Review review, Long reviewerId, Long veterinarianId);
    double getAverageRatingForVet(Long veterinarianId);
    void updateReview(Review review, Long reviewId);
    Page<Review> findAllReviewsByUserId(Long userId, int page, int size);
}
