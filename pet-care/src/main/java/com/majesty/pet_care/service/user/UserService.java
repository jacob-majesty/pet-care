package com.majesty.pet_care.service.user;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void add(User user) {  
        userRepository.save(user);
        
    }

}
