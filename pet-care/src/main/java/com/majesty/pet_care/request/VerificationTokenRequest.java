package com.majesty.pet_care.request;

import java.util.Date;

import com.majesty.pet_care.model.User;

import lombok.Data;

@Data
public class VerificationTokenRequest {
    private String token;
    private Date expirationTime;
    private User user;
}
