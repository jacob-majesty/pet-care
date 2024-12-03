package com.majesty.pet_care.service.appointment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.request.AppointmentRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    @Override
    public Appointment createAppointment(Appointment appointment, Long sender, Long recipient) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Appointment> getAllAppointments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
