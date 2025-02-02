package com.majesty.pet_care.service.pet;

import java.util.List;

import com.majesty.pet_care.model.Pet;

public interface IPetService {
    List<Pet> savePetsForAppointment(List<Pet> pets);

    List<Pet> savePetsForAppointment(Long appointmentId, List<Pet> pets);

    // List<Pet> savePetsForAppointment(Appointment appointment, List<Pet> pets);

    Pet updatePet(Pet pet, Long id);

    void deletePet(Long id);

    Pet getPetById(Long id);

    List<String> getPetTypes();

    List<String> getPetColors();

    List<String> getPetBreeds(String petType);
}
