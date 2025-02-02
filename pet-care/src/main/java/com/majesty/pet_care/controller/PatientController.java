package com.majesty.pet_care.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.patient.IPatientService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlMapping.PATIENTS)
public class PatientController {
    private final IPatientService patientService;

    @GetMapping(UrlMapping.GET_ALL_PATIENTS)
    public ResponseEntity<ApiResponse> getAllPatients() {
        List<UserDto> patients = patientService.getPatients();
        return ResponseEntity.ok(new ApiResponse(FeedbackMessage.RESOURCE_FOUND, patients));
    }
}
