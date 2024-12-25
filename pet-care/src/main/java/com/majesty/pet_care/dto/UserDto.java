package com.majesty.pet_care.dto;

import java.time.LocalDate;
import java.util.List;

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
    private String specialization;
    private LocalDate createdAt;
    private List<AppointmentDto> appointments;
    private List<ReviewDto> reviews;
    private long photoId;
    private byte[] photo;
    private double averageRating;

}
