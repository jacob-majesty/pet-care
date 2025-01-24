package com.majesty.pet_care.service.token;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majesty.pet_care.model.User;
import com.majesty.pet_care.model.VerificationToken;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.repository.VerificationTokenRepository;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.SystemUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationTokenService implements IVerificationTokenService {
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public String validateToken(String token) {
        Optional<VerificationToken> theToken = findByToken(token);
        if (theToken.isEmpty()) {
            return FeedbackMessage.INVALID_TOKEN;
        }
        User user = theToken.get().getUser();
        if (user.isEnabled()) {
            return FeedbackMessage.TOKEN_ALREADY_VERIFIED;
        }
        if (isTokenExpired(token)) {
            return FeedbackMessage.EXPIRED_TOKEN;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return FeedbackMessage.VALID_VERIFICATION_TOKEN;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);

    }

    @Transactional
    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        Optional<VerificationToken> theToken = findByToken(oldToken);
        if (theToken.isPresent()) {
            var verificationToken = theToken.get();
            verificationToken.setToken(UUID.randomUUID().toString());
            verificationToken.setExpirationDate(SystemUtils.getExpirationTime());
            return tokenRepository.save(verificationToken);
        } else {
            throw new IllegalArgumentException(FeedbackMessage.INVALID_VERIFICATION_TOKEN + oldToken);
        }

    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void deleteVerificationToken(Long tokenId) {
        tokenRepository.deleteById(tokenId);

    }

    @Override
    public boolean isTokenExpired(String token) {
        Optional<VerificationToken> theToken = findByToken(token);
        if (theToken.isEmpty()) {
            return true;
        }
        VerificationToken verificationToken = theToken.get();
        return verificationToken.getExpirationDate().getTime() <= Calendar.getInstance().getTime().getTime();
    }
}
