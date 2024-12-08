package com.majesty.pet_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
