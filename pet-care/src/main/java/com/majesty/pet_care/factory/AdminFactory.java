package com.majesty.pet_care.factory;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.Admin;
import com.majesty.pet_care.repository.AdminRepository;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.service.role.IRoleService;
import com.majesty.pet_care.service.user.UserAttributeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminFactory {

    private final AdminRepository adminRepository;
    private final UserAttributeMapper userAttributeMapper;
    private final IRoleService roleService;

    public Admin createAdmin(RegistrationRequest request) {
        Admin admin = new Admin();
        admin.setRoles(roleService.setUserRole("ADMIN"));
        userAttributeMapper.setCommonAttributes(request, admin);
        return adminRepository.save(admin);

    }

}
