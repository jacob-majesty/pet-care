package com.majesty.pet_care.factory;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.Veterinarian;
import com.majesty.pet_care.repository.VeterinarianRepository;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.service.user.UserAttributeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarianFactory {

    private final VeterinarianRepository veterinarianRepository;
    private final UserAttributeMapper userAttributeMapper;

    public Veterinarian createVeterinarian(RegistrationRequest request) {
        Veterinarian veterinarian = new Veterinarian();
        userAttributeMapper.setCommonAttributes(request, veterinarian);
        veterinarian.setSpecialization(request.getSpecialization());
        return veterinarianRepository.save(veterinarian);   
        
    }

}
