package com.majesty.pet_care.event;

import org.springframework.context.ApplicationEvent;

import com.majesty.pet_care.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;

    public RegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }
}