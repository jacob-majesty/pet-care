package com.majesty.pet_care.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.review.IReviewService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;
import com.majesty.pet_care.exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping(UrlMapping.REVIEWS)
@RestController
public class ReviewController {
    private final IReviewService reviewService;

    public ResponseEntity<ApiResponse> saveReview(@RequestBody Review review,
                                                  @RequestParam Long reviewerId,
                                                  @RequestParam Long veterinarianId) {
        try {
            Review savedReview = reviewService.saveReview(review, reviewerId, veterinarianId);
            return ResponseEntity.status(Response.SC_CREATED).body(new ApiResponse(FeedbackMessage.CREATE_SUCCESS, savedReview.getId()));
            
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(Response.SC_NOT_ACCEPTABLE).body(new ApiResponse(e.getMessage(), null));   
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(Response.SC_CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
        
    }

}
