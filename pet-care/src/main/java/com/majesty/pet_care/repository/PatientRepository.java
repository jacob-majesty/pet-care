package com.majesty.pet_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
