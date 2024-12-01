package com.majesty.pet_care.dto;


import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String UserType;
    private boolean isEnabled;

    @Transient
    private String specialization;

}
