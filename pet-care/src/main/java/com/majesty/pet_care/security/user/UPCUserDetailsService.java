package com.majesty.pet_care.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UPCUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(FeedbackMessage.RESOURCE_NOT_FOUND));
        return UPCUserDetails.buildUserDetails(user);
    }
}
