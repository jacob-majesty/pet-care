package com.majesty.pet_care.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.majesty.pet_care.enums.AppointmentStatus;

import lombok.Data;

@Data
public class AppointmentDto {

    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private LocalDate createdAt;
    private String reason;
    private AppointmentStatus status;
    private String appointmentNo;
    private PatientDto patient;
    private VeterinarianDto veterinarian;
    private List<PetDto> pets;

}
