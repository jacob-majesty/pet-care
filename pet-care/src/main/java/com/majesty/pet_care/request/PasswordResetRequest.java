package com.majesty.pet_care.request;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String token;
    private String newPassword;

}
