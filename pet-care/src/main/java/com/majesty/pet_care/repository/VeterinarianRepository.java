package com.majesty.pet_care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Veterinarian;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long>{

    List<Veterinarian> findBySpecialization(String specialization);

    boolean existsBySpecialization(String specialization);

}
