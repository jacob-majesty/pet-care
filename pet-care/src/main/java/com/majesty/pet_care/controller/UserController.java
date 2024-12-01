package com.majesty.pet_care.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.dto.EntityConverter;
import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.exception.UserAlreadyExistsException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.user.UserService;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody RegistrationRequest request) {
        try {
            User theUser = userService.add(request);
            UserDto registeredUser = entityConverter.mapEntityToDtoD(theUser, UserDto.class);
            return ResponseEntity.ok(new ApiResponse("User registered successfully",registeredUser));

        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));

        }
    }

}
