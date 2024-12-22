package com.majesty.pet_care.service.pet;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.Pet;
import com.majesty.pet_care.repository.PetRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetService implements IPetService {

    private final PetRepository petRepository;

    @Override
    public List<Pet> savePetsForAppointment(List<Pet> pets) {
        return petRepository.saveAll(pets);
    }

    @Override
    public Pet updatePet(Pet pet, long petId) {
        Pet existingPet = getPetById(petId);
        existingPet.setName(pet.getName());
        existingPet.setAge(pet.getAge());
        existingPet.setColor(pet.getColor());
        existingPet.setType(pet.getType());
        existingPet.setBreed(pet.getBreed());

        return petRepository.save(existingPet);

    }

    @Override
    public void deletePet(long petId) {
       petRepository.findById(petId)
                .ifPresentOrElse(petRepository::delete, 
                () ->{ throw new ResourceNotFoundException(FeedbackMessage.NOT_FOUND);});
    }

    @Override
    public Pet getPetById(long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException(FeedbackMessage.NOT_FOUND));
    }


}
