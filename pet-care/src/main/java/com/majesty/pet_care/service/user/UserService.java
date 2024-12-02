package com.majesty.pet_care.service.user;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.factory.UserFactory;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    @Override
    public User register(@RequestBody RegistrationRequest request) {  
        return userFactory.createUser(request);   
    }

    @Override
    public User update(Long UserId, UserUpdateRequest request) {
        User user = findById(UserId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber()); 
        user.setSpecialization(request.getSpecialization());
        return userRepository.save(user);
    }
    
    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RessourceNotFoundException("User not found"));
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
            .ifPresentOrElse(userRepository::delete, () ->{ 
                throw new RessourceNotFoundException("User not found");});

    }
}