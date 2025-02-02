package com.majesty.pet_care.service.patient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.dto.EntityConverter;
import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.model.Patient;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;
    private final EntityConverter<User, UserDto> entityConverter;

    @Override
    public List<UserDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(po -> entityConverter.mapEntityToDto(po, UserDto.class)).toList();
    }
}
