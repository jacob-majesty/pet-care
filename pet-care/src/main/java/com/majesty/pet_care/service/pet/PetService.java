package com.majesty.pet_care.service.pet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.model.Pet;
import com.majesty.pet_care.repository.AppointmentRepository;
import com.majesty.pet_care.repository.PetRepository;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetService implements IPetService {
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Pet> savePetsForAppointment(List<Pet> pets) {
        return petRepository.saveAll(pets);
    }

    @Override
    public List<Pet> savePetsForAppointment(Long appointmentId, List<Pet> pets) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(null);
        return pets.stream()
                .peek(pet -> pet.setAppointment(appointment))
                .map(petRepository::save)
                .collect(Collectors.toList());
    }

    @Override
    public Pet updatePet(Pet pet, Long petId) {
        Pet existingPet = getPetById(petId);
        existingPet.setName(pet.getName());
        existingPet.setAge(pet.getAge());
        existingPet.setColor(pet.getColor());
        existingPet.setType(pet.getType());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAge(pet.getAge());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePet(Long petId) {
        petRepository.findById(petId)
                .ifPresentOrElse(petRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException(FeedbackMessage.RESOURCE_NOT_FOUND);
                        });

    }

    @Override
    public Pet getPetById(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException(FeedbackMessage.RESOURCE_NOT_FOUND));
    }

    @Override
    public List<String> getPetTypes() {
        return petRepository.getDistinctPetTypes();
    }

    @Override
    public List<String> getPetColors() {
        return petRepository.getDistinctPetColors();
    }

    @Override
    public List<String> getPetBreeds(String petType) {
        return petRepository.getDistinctPetBreedsByPetType(petType);
    }

}