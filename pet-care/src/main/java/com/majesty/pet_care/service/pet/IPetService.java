package com.majesty.pet_care.service.pet;

import java.util.List;

import com.majesty.pet_care.model.Pet;

public interface IPetService {

    List<Pet> savePetsForAppointment(List<Pet> pets);
    Pet updatePet(Pet pet, long petId);
    void deletePet(long petId);
    Pet getPetById(long petId);

}
