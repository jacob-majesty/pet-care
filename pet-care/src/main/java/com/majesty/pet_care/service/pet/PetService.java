package com.majesty.pet_care.service.pet;

import java.util.List;

import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Pet;
import com.majesty.pet_care.repository.pet.PetRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

public class PetService implements IPetService {

    private PetRepository petRepository;

    @Override
    public List<Pet> savePetsForAppointments(List<Pet> pets) {
        return petRepository.saveAll(pets);
    }

    @Override
    public Pet updatePet(Pet pet, long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePet'");
    }

    @Override
    public void deletePet(long petId) {
       petRepository.findById(petId)
                .ifPresentOrElse(petRepository::delete, 
                () ->{ throw new RessourceNotFoundException(FeedbackMessage.NOT_FOUND);});
    }

    @Override
    public Pet getPetById(long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new RessourceNotFoundException(FeedbackMessage.NOT_FOUND));
    }


}
