package com.majesty.pet_care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.enums.AppointmentStatus;
import com.majesty.pet_care.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByAppointmentNo(String appointmentNo);

    boolean existsByPatientIdAndVeterinarianIdAndStatus(Long reviewerId, Long veterinarianId,
            AppointmentStatus appointmentStatus);

    List<Appointment> findAllByUserId(Long userId);

}
