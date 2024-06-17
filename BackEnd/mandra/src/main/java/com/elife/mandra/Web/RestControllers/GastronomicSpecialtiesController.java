package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.GastronomicSpecialtiesService;
import com.elife.mandra.DAO.Entities.GastronomicSpecialties;
import com.elife.mandra.Web.Requests.GastronomicSpecialtiesForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
@RequestMapping("/gastronomicspecialties")
public class GastronomicSpecialtiesController {


     final GastronomicSpecialtiesService gastronomicSpecialtiesService;

    public GastronomicSpecialtiesController(GastronomicSpecialtiesService gastronomicSpecialtiesService) {
        this.gastronomicSpecialtiesService = gastronomicSpecialtiesService;
    }



    // ---------------------------------- add Gastronomic Specialties by admin endpoint -----------------------------------

    @PostMapping("/{adminId}/add")
    public ResponseEntity<?> addGastronomicSpecialtie(@PathVariable Long adminId,
            @Valid @RequestPart("gastronomicSpecialtiesForm") GastronomicSpecialtiesForm gastronomicSpecialtiesForm,
            @RequestPart("image") MultipartFile image,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            GastronomicSpecialties addedGastronomicSpecialties = gastronomicSpecialtiesService
            .addGastronomicSpecialtie(adminId, gastronomicSpecialtiesForm, image);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(addedGastronomicSpecialties);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




     // ---------------------------------- update Gastronomic Specialties endpoint -----------------------------------

    @PutMapping("/{gastronomicspecialtieId}/edit")
    public ResponseEntity<Object> updateGastronomicSpecialtie(@PathVariable(value = "gastronomicspecialtieId") Long gastronomicspecialtieId,
            @Valid @RequestBody GastronomicSpecialtiesForm gastronomicSpecialtiesForm,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            GastronomicSpecialties updatedgGastronomicSpecialtie = gastronomicSpecialtiesService.updateGastronomicSpecialtie(gastronomicspecialtieId, gastronomicSpecialtiesForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedgGastronomicSpecialtie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




    // ----------------------------------      edit Gastronomic Specialties image endpoint     -----------------------------------

   @PutMapping("/{gastronomicspecialtieId}/edit-image")
    public ResponseEntity<Object> updatedGastronomicSpecialtie(@PathVariable(value = "gastronomicspecialtieId") Long gastronomicspecialtieId, 
                                                    @RequestParam("image") MultipartFile image) {
        try {
            GastronomicSpecialties updatedgGastronomicSpecialtie = gastronomicSpecialtiesService
            .updateGastronomicSpecialtieImage(gastronomicspecialtieId, image);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedgGastronomicSpecialtie);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while updating gastronomic specialtie image", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
