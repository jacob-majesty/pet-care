package com.majesty.pet_care.service.user;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.majesty.pet_care.factory.UserFactory;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFactory userFactory;

    public User add(@RequestBody RegistrationRequest request) {  
        return userFactory.createUser(request);
        
    }

}
