package com.majesty.pet_care.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private long id;    
    private String feedback;
    private int stars;

}
