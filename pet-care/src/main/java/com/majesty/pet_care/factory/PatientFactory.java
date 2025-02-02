package com.majesty.pet_care.factory;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.Patient;
import com.majesty.pet_care.repository.PatientRepository;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.service.role.IRoleService;
import com.majesty.pet_care.service.user.UserAttributeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientFactory {

    private final PatientRepository patientRepository;
    private final UserAttributeMapper userAttributeMapper;
    private final IRoleService roleService;

    public Patient createPatient(RegistrationRequest request) {
        Patient patient = new Patient();
        patient.setRoles(roleService.setUserRole("PATIENT"));
        userAttributeMapper.setCommonAttributes(request, patient);
        return patientRepository.save(patient);

    }

}
