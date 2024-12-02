package com.majesty.pet_care.service.user;

import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.request.UserUpdateRequest;

public interface IUserService {

    User register(RegistrationRequest request);
    User update(Long UserId, UserUpdateRequest request);
    User findById(Long userId);
    void delete(Long userId);

}
