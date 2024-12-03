package com.majesty.pet_care.service.appointment;

import java.util.List;

import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.request.AppointmentUpdateRequest;

public interface IAppointmentService {

    Appointment createAppointment(Appointment appointment, Long sender, Long recipient);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, AppointmentUpdateRequest request);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByNo(String appointmentNo);

}
