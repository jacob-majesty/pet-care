package com.majesty.pet_care.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.request.AppointmentUpdateRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.appointment.AppointmentService;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all") 
    public ResponseEntity<ApiResponse> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.FOUND, appointments));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }            
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<ApiResponse> bookAppointment(
            @RequestBody Appointment appointment,
            @RequestParam Long senderId,
            @RequestParam Long recipientId) {

                try {
                    Appointment theAppointment = appointmentService.createAppointment(appointment, senderId, recipientId);
                    return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.SUCCESS, theAppointment));


                } catch (RessourceNotFoundException e) {
                    return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
                    
                } catch (Exception e) {
                    return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
                    
                }            
    }


    @GetMapping("/appointment/{id}")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.FOUND, appointment));
            
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }    
    }

    @GetMapping("/appointment/{appointmentNo}")
    public ResponseEntity<ApiResponse> getAppointmentByNo(@PathVariable String appointmentNo) {
        try {
            Appointment appointment = appointmentService.getAppointmentByNo(appointmentNo);
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.FOUND, appointment));
            
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }    
    }


    @DeleteMapping("/appointment/{id}/delete")
    public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.DELETE_SUCCESS, null));
            
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }
    }

    @PutMapping("/appointment/{id}/update")
    public ResponseEntity<ApiResponse> updateAppointment(
            @PathVariable Long id, 
            @RequestBody AppointmentUpdateRequest request) {
                
        try {
            Appointment theAppointment = appointmentService.updateAppointment(id, request);
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.UPDATE_SUCCESS, theAppointment));
            
        } catch (IllegalStateException e) {
            return ResponseEntity.status(Response.SC_NOT_ACCEPTABLE).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }
    }

}
