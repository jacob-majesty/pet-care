package com.majesty.pet_care.service.user;

import java.sql.SQLException;
import java.util.List;

import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.request.UserUpdateRequest;

public interface IUserService {

    User register(RegistrationRequest request);
    User update(Long UserId, UserUpdateRequest request);
    User findById(Long userId);
    void delete(Long userId);
    List<UserDto> getAllUsers();
    UserDto getUserWithDetails(Long userId) throws SQLException;

}
