package com.majesty.pet_care.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.service.user.UserService;

import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public User add(@RequestBody RegistrationRequest request) {
        return userService.add(request);
    }

}
