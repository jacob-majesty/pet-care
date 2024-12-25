package com.majesty.pet_care.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private long id;    
    private String feedback;
    private int stars;
    private Long vetId;
    private String vetName;
    private Long reviewId;
    private String reviewName;
    private byte[] reviewImage;
    private byte[] vetImage;

}
