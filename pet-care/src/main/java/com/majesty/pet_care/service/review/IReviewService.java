package com.majesty.pet_care.service.review;

import org.springframework.data.domain.Page;

import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.request.ReviewUpdateRequest;

public interface IReviewService {
    Review saveReview(Review review, Long reviewerId, Long veterinarianId);
    double getAverageRatingForVet(Long veterinarianId);
    Review updateReview(Long reviewId, ReviewUpdateRequest review);
    Page<Review> findAllReviewsByUserId(Long userId, int page, int size);
    void deleteReview(Long reviewerId);
}
