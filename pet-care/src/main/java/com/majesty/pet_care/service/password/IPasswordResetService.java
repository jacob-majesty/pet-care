package com.majesty.pet_care.service.password;

import java.util.Optional;

import com.majesty.pet_care.model.User;

public interface IPasswordResetService {

    Optional<User> findUserByPasswordResetToken(String token);

    void requestPasswordReset(String email);

    String resetPassword(String password, User user);
}
