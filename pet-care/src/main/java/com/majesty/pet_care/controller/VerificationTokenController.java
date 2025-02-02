package com.majesty.pet_care.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.model.User;
import com.majesty.pet_care.model.VerificationToken;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.VerificationTokenRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.token.IVerificationTokenService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlMapping.TOKEN_VERIFICATION)
public class VerificationTokenController {

    private final IVerificationTokenService verificationTokenService;
    private final UserRepository userRepository;

    @GetMapping(UrlMapping.VALIDATE_TOKEN)
    public ResponseEntity<ApiResponse> validateToken(String token) {
        String result = verificationTokenService.validateToken(token);
        ApiResponse response = switch (result) {
            case "INVALID" -> new ApiResponse(FeedbackMessage.INVALID_TOKEN, null);
            case "VERIFIED" -> new ApiResponse(FeedbackMessage.TOKEN_ALREADY_VERIFIED, null);
            case "EXPIRED" -> new ApiResponse(FeedbackMessage.EXPIRED_TOKEN, null);
            case "VALID" -> new ApiResponse(FeedbackMessage.VALID_VERIFICATION_TOKEN, null);
            default -> new ApiResponse(FeedbackMessage.TOKEN_VALIDATION_ERROR, null);
        };
        return ResponseEntity.ok(response);
    }

    @GetMapping(UrlMapping.CHECK_TOKEN_EXPIRATION)
    public ResponseEntity<ApiResponse> checkTokenExpiration(String token) {
        boolean isExpired = verificationTokenService.isTokenExpired(token);
        ApiResponse response;
        if (isExpired) {
            response = new ApiResponse(FeedbackMessage.EXPIRED_TOKEN, null);
        } else {
            response = new ApiResponse(FeedbackMessage.VALID_VERIFICATION_TOKEN, null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(UrlMapping.SAVE_TOKEN)
    public ResponseEntity<ApiResponse> saveVerificationTokenForUser(@RequestBody VerificationTokenRequest request) {
        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new RuntimeException(FeedbackMessage.USER_FOUND));
        verificationTokenService.saveVerificationTokenForUser(request.getToken(), user);
        return ResponseEntity.ok(new ApiResponse(FeedbackMessage.TOKEN_SAVED_SUCCESS, null));
    }

    @PutMapping(UrlMapping.GENERATE_NEW_TOKEN_FOR_USER)
    public ResponseEntity<ApiResponse> generateNewVerificationToken(@RequestParam String oldToken) {
        VerificationToken newToken = verificationTokenService.generateNewVerificationToken(oldToken);
        return ResponseEntity.ok(new ApiResponse("", newToken));
    }

    @DeleteMapping(UrlMapping.DELETE_TOKEN)
    public ResponseEntity<ApiResponse> deleteUserToken(@RequestParam Long userId) {
        verificationTokenService.deleteVerificationToken(userId);
        return ResponseEntity.ok(new ApiResponse(FeedbackMessage.TOKEN_DELETE_SUCCESS, null));
    }

}
