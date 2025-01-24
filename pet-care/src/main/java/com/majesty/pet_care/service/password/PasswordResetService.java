package com.majesty.pet_care.service.password;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.majesty.pet_care.event.PasswordResetEvent;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.model.VerificationToken;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.repository.VerificationTokenRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService implements IPasswordResetService {
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<User> findUserByPasswordResetToken(String token) {
        return tokenRepository.findByToken(token).map(VerificationToken::getUser);
    }

    @Override
    public void requestPasswordReset(String email) {
        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            PasswordResetEvent passwordResetEvent = new PasswordResetEvent(this, user);
            eventPublisher.publishEvent(passwordResetEvent);
        }, () -> {
            throw new ResourceNotFoundException(FeedbackMessage.NO_USER_FOUND + email);
        });

    }

    @Override
    public String resetPassword(String password, User user) {
        try {
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return FeedbackMessage.PASSWORD_RESET_SUCCESS;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
