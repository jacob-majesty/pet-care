package com.majesty.pet_care.service.pet;

import java.util.List;

import com.majesty.pet_care.model.Pet;

public interface IPetService {

    List<Pet> savePetsForAppointments(List<Pet> pets);
    Pet updatePet(Pet pet, long Id);
    void deletePet(long Id);
    Pet getPetById(long Id);

}
