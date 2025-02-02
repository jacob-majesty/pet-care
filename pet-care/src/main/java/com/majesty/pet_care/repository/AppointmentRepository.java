package com.majesty.pet_care.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.majesty.pet_care.enums.AppointmentStatus;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.model.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        Appointment findByAppointmentNo(String appointmentNo);

        boolean existsByVeterinarianIdAndPatientIdAndStatus(Long veterinarianId, Long reviewerId,
                        AppointmentStatus appointmentStatus);

        @Query("SELECT a FROM Appointment a WHERE a.patient.id =:userId OR a.veterinarian.id =:userId ")
        List<Appointment> findAllByUserId(@Param("userId") Long userId);

        List<Appointment> findByVeterinarianAndAppointmentDate(User veterinarian, LocalDate requestedDate);
}
