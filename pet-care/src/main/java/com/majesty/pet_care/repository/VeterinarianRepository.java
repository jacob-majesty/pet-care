package com.majesty.pet_care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.majesty.pet_care.model.Veterinarian;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    List<Veterinarian> findBySpecialization(String specialization);

    boolean existsBySpecialization(String specialization);

    @Query("SELECT DISTINCT v.specialization FROM Veterinarian v")
    List<String> getSpecializations();

}
