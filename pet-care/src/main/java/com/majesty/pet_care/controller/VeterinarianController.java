package com.majesty.pet_care.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.appointment.IAppointmentService;
import com.majesty.pet_care.service.veterinarian.IVeterinarianService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping(UrlMapping.VETERINARIANS)
@RequiredArgsConstructor
public class VeterinarianController {
    private final IVeterinarianService veterinarianService;
    private final IAppointmentService appointmentService;

    @GetMapping(UrlMapping.GET_ALL_VETERINARIANS)
    public ResponseEntity<ApiResponse> getAllVeterinarians() {
        List<UserDto> allVeterinarians = veterinarianService.getAllVeterinariansWithDetails();
        return ResponseEntity.ok(new ApiResponse(FeedbackMessage.FOUND, allVeterinarians));
    }

    @GetMapping(UrlMapping.SEARCH_VETERINARIAN_FOR_APPOINTMENT)
    public ResponseEntity<ApiResponse> searchVeterinariansForAppointment(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalTime time,
            @RequestParam String specialization) {

        try {
            List<UserDto> availableVeterinarians = veterinarianService.findAvailableVetsForAppointment(specialization,
                    date, time);
            if (availableVeterinarians.isEmpty()) {
                return ResponseEntity.status(Response.SC_NOT_FOUND)
                        .body(new ApiResponse(FeedbackMessage.NO_VETS_AVAILABLE, null));
            }
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.FOUND, availableVeterinarians));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_ALL_SPECIALIZATIONS)
    public ResponseEntity<ApiResponse> getAllSpecializations() {
        try {
            List<String> specializations = veterinarianService.getSpecializations();
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.FOUND, specializations));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.VET_AGGREGATE_BY_SPECIALIZATION)
    public ResponseEntity<List<Map<String, Object>>> aggregateVetsBySpecialization() {
        List<Map<String, Object>> aggregatedVets = veterinarianService.aggregateVetsBySpecialization();
        return ResponseEntity.ok(aggregatedVets);
    }

}
