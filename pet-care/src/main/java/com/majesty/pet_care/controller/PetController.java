package com.majesty.pet_care.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.exception.RessourceNotFoundException;
import com.majesty.pet_care.model.Pet;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.pet.IPetService;
import com.majesty.pet_care.utils.FeedbackMessage;

import lombok.RequiredArgsConstructor;
import com.majesty.pet_care.utils.UrlMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(UrlMapping.PETS)
@RequiredArgsConstructor
public class PetController {

    private final IPetService petService;

    @PostMapping(UrlMapping.SAVE_PETS_FOR_APPOINTMENT)
    public ResponseEntity<ApiResponse> savePets(@RequestBody List<Pet> pets){
        try {
            List<Pet> savedPets = petService.savePetsForAppointment(pets);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.CREATE_SUCCESS, savedPets));
        } catch (RuntimeException e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_PET_BY_ID)
    public ResponseEntity<ApiResponse> getPetById(@PathVariable Long petId){
        try {
            Pet pet = petService.getPetById(petId);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.FOUND, pet));
            
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }

    }

    @DeleteMapping(UrlMapping.DELETE_PET_BY_ID)
    public ResponseEntity<ApiResponse> deletePetById(@PathVariable Long petId){
        try {
            petService.deletePet(petId);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.DELETE_SUCCESS, null));
            
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }

    }

    @PutMapping(UrlMapping.UPDATE_PET)
    public ResponseEntity<ApiResponse> updatePet(@PathVariable Long petId, @RequestBody Pet pet){
        try {
            Pet updatedPet = petService.updatePet(pet, petId);
            return ResponseEntity.status(Response.SC_OK).body(new ApiResponse(FeedbackMessage.UPDATE_SUCCESS, updatedPet));
            
        } catch (RessourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }
    }

}
