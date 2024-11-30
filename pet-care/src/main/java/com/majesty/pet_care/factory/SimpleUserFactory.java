package com.majesty.pet_care.factory;

import org.springframework.stereotype.Component;

import com.majesty.pet_care.exception.UserAlreadyExistsException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.RegistrationRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SimpleUserFactory implements UserFactory {

    private final UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final PatientFactory patientFactory;
    private final AdminFactory adminFactory;

    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        if(userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("Oops! " + registrationRequest.getEmail() + " already exists.");
        }
        switch (registrationRequest.getUserType()) {
            case "VET" ->{return veterinarianFactory.createVeterinarian(registrationRequest);}

            case "PATIENT" ->{return patientFactory.createPatient(registrationRequest);}

            case "ADMIN" ->{return adminFactory.createAdmin(registrationRequest);}
        
            default -> {return null;}
        }

    }
}