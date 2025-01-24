package com.majesty.pet_care.event;

import org.springframework.context.ApplicationEvent;

import com.majesty.pet_care.model.Appointment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentApprovedEvent extends ApplicationEvent {
    private final Appointment appointment;

    public AppointmentApprovedEvent(Appointment appointment) {
        super(appointment);
        this.appointment = appointment;
    }
}
