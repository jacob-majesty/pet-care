package com.majesty.pet_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
