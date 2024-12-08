package com.majesty.pet_care.service.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.majesty.pet_care.enums.AppointmentStatus;
import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.model.Pet;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.repository.AppointmentRepository;
import com.majesty.pet_care.repository.PetRepository;
import com.majesty.pet_care.repository.UserRepository;
import com.majesty.pet_care.request.AppointmentUpdateRequest;
import com.majesty.pet_care.request.BookAppointmentRequest;
import com.majesty.pet_care.service.pet.IPetService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final IPetService petService;

    @Transactional
    @Override
    public Appointment createAppointment(BookAppointmentRequest request, Long senderId, Long recipientId) {
    
                Optional<User> sender = userRepository.findById(senderId);
                Optional<User> recipient = userRepository.findById(recipientId);

                if (sender.isPresent() && recipient.isPresent()) {
                    Appointment appointment = request.getAppointment();
                    List<Pet> pets = request.getPets();
                    pets.forEach(pet -> pet.setAppointment(appointment));
                    List<Pet> savedPets = petService.savePetsForAppointment(pets);
                    appointment.setPets(savedPets);

                    appointment.addPatient(sender.get());
                    appointment.addVeterinarian(recipient.get());
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
        appointmentRepository.findById(id)
            .ifPresentOrElse(appointmentRepository::delete, () ->{ throw new RessourceNotFoundException("Appointment not found");});
        
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
    public Appointment updateAppointment(Long id, AppointmentUpdateRequest request) {

        Appointment existingAppointment = getAppointmentById(id);
        if(!Objects.equals(existingAppointment.getStatus(), AppointmentStatus.WAITING_FOR_APPROVAL)) {
            throw new IllegalStateException("Sorry, this appointment can no longer be updated.");
        }
        existingAppointment.setAppointmentDate(LocalDate.parse(request.getAppointmentDate()));
        existingAppointment.setAppointmentTime(LocalTime.parse(request.getAppointmentTime()));
        existingAppointment.setReason(request.getReason());
        
        return appointmentRepository.save(existingAppointment);
    }

}
