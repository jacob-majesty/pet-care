package com.majesty.pet_care.service.patient;

import java.util.List;

import com.majesty.pet_care.dto.UserDto;

public interface IPatientService {
    List<UserDto> getPatients();
}