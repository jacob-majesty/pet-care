package com.majesty.pet_care.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.event.RegistrationCompleteEvent;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.model.VerificationToken;
import com.majesty.pet_care.request.LoginRequest;
import com.majesty.pet_care.request.PasswordResetRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.response.JwtResponse;
import com.majesty.pet_care.security.jwt.JwtUtils;
import com.majesty.pet_care.security.user.UPCUserDetails;
import com.majesty.pet_care.service.password.PasswordResetService;
import com.majesty.pet_care.service.token.VerificationTokenService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(UrlMapping.AUTH)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final VerificationTokenService tokenService;
    private final PasswordResetService passwordResetService;
    private final ApplicationEventPublisher publisher;

    @PostMapping(UrlMapping.LOGIN)
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokenForUser(authentication);
            UPCUserDetails userDetails = (UPCUserDetails) authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(userDetails.getId(), jwt);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(FeedbackMessage.AUTHENTICATION_SUCCESS, jwtResponse));

        } catch (DisabledException e) {
            return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED).body(new ApiResponse(FeedbackMessage.ACCOUNT_DISABLED, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage(), FeedbackMessage.INVALID_PASSWORD));

        }
    }

    @GetMapping(UrlMapping.VERIFY_EMAIL)
    public ResponseEntity<ApiResponse> verifyEmail(@RequestParam("token") String token) {
        String result = tokenService.validateToken(token);
        return switch (result) {
            case "VALID" -> ResponseEntity.ok(new ApiResponse(FeedbackMessage.VALID_VERIFICATION_TOKEN, null));
            case "VERIFIED" -> ResponseEntity.ok(new ApiResponse(FeedbackMessage.TOKEN_ALREADY_VERIFIED, null));
            case "EXPIRED" ->
                ResponseEntity.status(HttpStatus.GONE).body(new ApiResponse(FeedbackMessage.EXPIRED_TOKEN, null));
            case "INVALID" ->
                ResponseEntity.status(HttpStatus.GONE)
                        .body(new ApiResponse(FeedbackMessage.INVALID_VERIFICATION_TOKEN, null));
            default -> ResponseEntity.internalServerError().body(new ApiResponse(FeedbackMessage.ERROR, null));

        };
    }

    @PutMapping(UrlMapping.RESEND_VERIFICATION_TOKEN)
    public ResponseEntity<ApiResponse> resendVerificationToken(@RequestParam("token") String oldToken) {
        try {
            VerificationToken verificationToken = tokenService.generateNewVerificationToken(oldToken);
            User theUser = verificationToken.getUser();
            publisher.publishEvent(new RegistrationCompleteEvent(theUser));
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.NEW_VERIFICATION_TOKEN_SENT, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(UrlMapping.REQUEST_PASSWORD_RESET)
    public ResponseEntity<ApiResponse> requestPasswordReset(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(FeedbackMessage.INVALID_EMAIL, null));
        }
        try {
            passwordResetService.requestPasswordReset(email);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.PASSWORD_RESET_EMAIL_SENT, null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(UrlMapping.RESET_PASSWORD)
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody PasswordResetRequest request) {
        String token = request.getToken();
        String newPassword = request.getNewPassword();
        if (token == null || token.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(FeedbackMessage.MISSING_PASSWORD, null));
        }
        Optional<User> theUser = passwordResetService.findUserByPasswordResetToken(token);
        if (theUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(FeedbackMessage.INVALID_RESET_TOKEN, null));
        }
        User user = theUser.get();
        String message = passwordResetService.resetPassword(newPassword, user);
        return ResponseEntity.ok(new ApiResponse(message, null));
    }

}
