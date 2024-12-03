package com.majesty.pet_care.service.appointment;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.majesty.pet_care.enums.AppointmentStatus;
import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.AppointmentRepository;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.AppointmentRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId) {
    
                Optional<User> sender = userRepository.findById(senderId);
                Optional<User> recipient = userRepository.findById(recipientId);

                if (sender.isPresent() && recipient.isPresent()) {
                    appointment.setPatient(sender.get());
                    appointment.setVeterinarian(recipient.get());
                    appointment.setAppointmentNo();
                    appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
                    return appointmentRepository.save(appointment);
                }
        throw new RessourceNotFoundException("Sender or recipient not found");
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentRepository.findAll();
        
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id).ifPresent(appointmentRepository::delete);
        
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new RessourceNotFoundException("Appointment not found"));
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo);
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
