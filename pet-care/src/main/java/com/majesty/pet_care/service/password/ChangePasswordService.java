package com.majesty.pet_care.service.password;

import java.util.Objects;
import org.springframework.stereotype.Service;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChangePasswordService implements IChangePasswordService {
    private final UserRepository userRepository;

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (Objects.equals(request.getCurrentPassword(), "")
                || Objects.equals(request.getNewPassword(), "")) {
            throw new IllegalArgumentException("All fields are required");
        }
        if (!Objects.equals(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password does not match");
        }
        if (!Objects.equals(request.getNewPassword(), request.getConfirmNewPassword())) {
            throw new IllegalArgumentException("Password confirmation mis-match ");
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);

    }

}
