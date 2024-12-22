package com.majesty.pet_care.request;

import lombok.Data;
@Data
public class ReviewUpdateRequest {
    private int stars;
    private String feedback;
}


