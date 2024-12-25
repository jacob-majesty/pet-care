package com.majesty.pet_care.service.appointment;

import java.util.List;

import com.majesty.pet_care.dto.AppointmentDto;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.request.AppointmentUpdateRequest;
import com.majesty.pet_care.request.BookAppointmentRequest;

public interface IAppointmentService {

    Appointment createAppointment(BookAppointmentRequest appointment, Long sender, Long recipient);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, AppointmentUpdateRequest request);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByNo(String appointmentNo);
    List<AppointmentDto> getUserAppointments(Long userId);

}
