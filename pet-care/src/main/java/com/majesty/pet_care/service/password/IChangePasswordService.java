package com.majesty.pet_care.service.password;

import com.majesty.pet_care.request.ChangePasswordRequest;

public interface IChangePasswordService {
    void changePassword(Long userId, ChangePasswordRequest request);
}
