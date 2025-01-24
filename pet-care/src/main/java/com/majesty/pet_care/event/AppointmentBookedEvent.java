package com.majesty.pet_care.event;

import org.springframework.context.ApplicationEvent;

import com.majesty.pet_care.model.Appointment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentBookedEvent extends ApplicationEvent {
    private Appointment appointment;

    public AppointmentBookedEvent(Appointment appointment) {
        super(appointment);
        this.appointment = appointment;
    }
}
