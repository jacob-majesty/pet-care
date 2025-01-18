package com.majesty.pet_care.controller;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.majesty.pet_care.model.Review;
import com.majesty.pet_care.request.ReviewUpdateRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.review.IReviewService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;
import com.majesty.pet_care.dto.ReviewDto;
import com.majesty.pet_care.exception.AlreadyExistsException;
import com.majesty.pet_care.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.REVIEWS)
@RestController
public class ReviewController {
    private final IReviewService reviewService;
    private final ModelMapper modelMapper;

    @PostMapping(UrlMapping.SUBMIT_REVIEW)
    public ResponseEntity<ApiResponse> saveReview(@RequestBody Review review,
            @RequestParam Long reviewerId,
            @RequestParam Long vetId) {
        try {
            Review savedReview = reviewService.saveReview(review, reviewerId, vetId);
            return ResponseEntity.status(Response.SC_CREATED)
                    .body(new ApiResponse(FeedbackMessage.CREATE_SUCCESS, savedReview.getId()));

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(Response.SC_NOT_ACCEPTABLE)
                    .body(new ApiResponse(FeedbackMessage.NOT_ALLOWED, null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(Response.SC_CONFLICT)
                    .body(new ApiResponse(FeedbackMessage.SERVER_ERROR, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(FeedbackMessage.NOT_FOUND, null));
        }
    }

    @GetMapping(UrlMapping.GET_USER_REVIEWS)
    public ResponseEntity<ApiResponse> getReviewByUserId(@PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, page, size);
        Page<ReviewDto> reviewDtos = reviewPage.map(element -> modelMapper.map(element, ReviewDto.class));
        return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.FOUND, reviewDtos));
    }

    @PutMapping(UrlMapping.UPDATE_REVIEW)
    public ResponseEntity<ApiResponse> updateReview(@PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequest updateRequest) {

        try {
            Review updatedReview = reviewService.updateReview(reviewId, updateRequest);
            return ResponseEntity.status(Response.SC_OK)
                    .body(new ApiResponse(FeedbackMessage.UPDATE_SUCCESS, updatedReview.getId()));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }

    }

    @DeleteMapping(UrlMapping.DELETE_REVIEW)
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.DELETE_SUCCESS, null));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping(UrlMapping.GET_AVERAGE_RATING)
    public ResponseEntity<ApiResponse> getAverageRatingForVet(@PathVariable Long vetId) {
        double averageRating = reviewService.getAverageRatingForVet(vetId);
        return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.FOUND, averageRating));
    }

}
