package com.majesty.pet_care.factory;


import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;

public interface UserFactory {

    public User createUser(RegistrationRequest registrationRequest);

}
