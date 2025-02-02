package com.majesty.pet_care.dto;

import lombok.Data;

@Data
public class VeterinarianDto {
    private Long veterinarianId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String specialization;

}
