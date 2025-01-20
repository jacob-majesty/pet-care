package com.majesty.pet_care.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.majesty.pet_care.dto.EntityConverter;
import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.exception.AlreadyExistsException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.ChangePasswordRequest;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.request.UserUpdateRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.password.IChangePasswordService;
import com.majesty.pet_care.service.user.UserService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.USERS)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;
    private final IChangePasswordService changePasswordService;

    @PostMapping(UrlMapping.REGISTER_USER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest request) {
        try {
            User theUser = userService.register(request);
            UserDto registeredUser = entityConverter.mapEntityToDto(theUser, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.CREATE_SUCCESS, registeredUser));

        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(Response.SC_CONFLICT).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }

    }

    @PutMapping(UrlMapping.UPDATE_USER)
    public ResponseEntity<ApiResponse> update(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {

        try {
            User user = userService.update(userId, request);
            UserDto userDto = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.UPDATE_SUCCESS, userDto));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }

    }

    @GetMapping(UrlMapping.GET_USER_BY_ID)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long userId) {
        try {
            UserDto userDto = userService.getUserWithDetails(userId);
            // UserDto theUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.FOUND, userDto));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @DeleteMapping(UrlMapping.DELETE_USER_BY_ID)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.DELETE_SUCCESS, null));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping(UrlMapping.GET_ALL_USERS)
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDto> theUsers = userService.getAllUsers();
        return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.FOUND, theUsers));
    }

    @PutMapping(UrlMapping.CHANGE_PASSWORD)
    public ResponseEntity<ApiResponse> changePassword(@PathVariable Long userId,
            @RequestBody ChangePasswordRequest request) {
        try {
            changePasswordService.changePassword(userId, request);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.CREATE_SUCCESS, null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.Count_All_VETS)
    public long countVeterinarians() {
        return userService.countVeterinarians();
    }

    @GetMapping(UrlMapping.Count_All_PATIENTS)
    public long countPatients() {
        return userService.countPatients();
    }

    @GetMapping(UrlMapping.Count_All_USERS)
    public long countUsers() {
        return userService.countAllUsers();
    }

    @GetMapping(UrlMapping.AGGREGATE_USERS)
    public ResponseEntity<ApiResponse> aggregateUsersByMonthAndType() {
        try {
            Map<String, Map<String, Long>> aggregatedUsers = userService.aggregateUsersByMonthAndType();
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.FOUND, aggregatedUsers));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/account/aggregated-by-status")
    public ResponseEntity<ApiResponse> getAggregatedUsersByEnabledStatus() {
        try {
            Map<String, Map<String, Long>> aggregatedData = userService.aggregateUsersByEnabledStatusAndType();
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.FOUND, aggregatedData));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/account/{userId}/lock-user-account")
    public ResponseEntity<ApiResponse> lockUserAccount(@PathVariable Long userId) {
        try {
            userService.lockUserAccount(userId);
            return ResponseEntity.ok(new ApiResponse("User account locked successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/account/{userId}/unLock-user-account")
    public ResponseEntity<ApiResponse> unLockUserAccount(@PathVariable Long userId) {
        try {
            userService.unLockUserAccount(userId);
            return ResponseEntity.ok(new ApiResponse("User account unlocked successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}