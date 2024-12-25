package com.majesty.pet_care.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.majesty.pet_care.enums.AppointmentStatus;

public class AppointmentDto {

    private long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private LocalDate createdAt;
    private String reason;
    private AppointmentStatus status;
    private UserDto patient;
    private UserDto veterinarian;
    private String appointmentNo;
    private List<PetDto> pets;

}
