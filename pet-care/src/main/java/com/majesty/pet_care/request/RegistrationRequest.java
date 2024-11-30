package com.majesty.pet_care.request;

import lombok.Data;

@Data
public class RegistrationRequest {

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String password;
    private String UserType;
    private boolean isEnabled;
    private String specialization;

}
