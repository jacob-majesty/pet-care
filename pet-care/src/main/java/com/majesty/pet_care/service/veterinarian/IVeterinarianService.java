package com.majesty.pet_care.service.veterinarian;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.model.Veterinarian;

public interface IVeterinarianService {

    List<UserDto> getAllVeterinariansWithDetails();

    List<UserDto> findAvailableVetsForAppointment(String specialization, LocalDate date, LocalTime time);

    List<Veterinarian> getVeterinariansBySpecialization(String specialization);

    List<String> getSpecializations();

    List<Map<String, Object>> aggregateVetsBySpecialization();

}
