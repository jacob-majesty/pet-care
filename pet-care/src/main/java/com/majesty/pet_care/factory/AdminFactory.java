package com.majesty.pet_care.factory;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.Admin;
import com.majesty.pet_care.repository.AdminRepository;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.service.user.UserAttributeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminFactory {

    private final AdminRepository adminRepository;
    private final UserAttributeMapper userAttributeMapper;

    public Admin createAdmin(RegistrationRequest registrationRequest) {
        Admin admin = new Admin();
        userAttributeMapper.setCommonAttributes(registrationRequest, admin);
        return adminRepository.save(admin);
        
    }

}
