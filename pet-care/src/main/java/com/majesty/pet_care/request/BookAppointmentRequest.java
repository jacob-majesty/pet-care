package com.majesty.pet_care.request;

import java.util.List;

import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.model.Pet;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookAppointmentRequest {

    private Appointment appointment;
    private List<Pet> pets;

}
