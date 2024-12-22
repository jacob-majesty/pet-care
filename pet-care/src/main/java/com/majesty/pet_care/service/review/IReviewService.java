package com.majesty.pet_care.service.review;

import org.hibernate.query.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import com.majesty.pet_care.model.Review;

public interface IReviewService {
    void saveReview(Review review, Long reviewerId, Long veterinarianId);
    double getAverageRatingForVet(Long veterinarianId);
    void updateReview(Review review, Long reviewId);
    Page<Review> getReviewsForVet(Long veterinarianId, Pageable pageable);


}
