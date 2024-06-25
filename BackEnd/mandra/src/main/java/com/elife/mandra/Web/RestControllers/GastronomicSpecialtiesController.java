package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.GastronomicSpecialtiesService;
import com.elife.mandra.DAO.Entities.GastronomicSpecialties;
import com.elife.mandra.Web.Requests.GastromnmicSpecialtiesForms.GastronomicSpecialtiesForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PreAuthorize("hasAnyRole('Admin') and hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<?> addGastronomicSpecialtie(@PathVariable Long adminId,
            @Valid @RequestPart("gastronomicSpecialtiesForm") GastronomicSpecialtiesForm gastronomicSpecialtiesForm,
            @RequestPart("image") MultipartFile image,
            BindingResult result) {
        try{           
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            GastronomicSpecialties addedGastronomicSpecialties = gastronomicSpecialtiesService
            .addGastronomicSpecialtie(adminId, gastronomicSpecialtiesForm, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedGastronomicSpecialties);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while adding gastronomic specialtie", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




     // ---------------------------------- update Gastronomic Specialties endpoint -----------------------------------

    @PutMapping("/{gastronomicspecialtieId}/edit")
    @PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateGastronomicSpecialtie(@PathVariable(value = "gastronomicspecialtieId") Long gastronomicspecialtieId,
            @Valid @RequestBody GastronomicSpecialtiesForm gastronomicSpecialtiesForm,
            BindingResult result) {
        try{            
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            GastronomicSpecialties updatedGastronomicSpecialtie = gastronomicSpecialtiesService.updateGastronomicSpecialtie(gastronomicspecialtieId, gastronomicSpecialtiesForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedGastronomicSpecialtie);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while updating gastronomic specialtie", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




    // ----------------------------------      edit Gastronomic Specialties image endpoint     -----------------------------------

   @PutMapping("/{gastronomicspecialtieId}/edit-image")
   @PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateGastronomicSpecialtie(@PathVariable(value = "gastronomicspecialtieId") Long gastronomicspecialtieId, 
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




    // ---------------------------------- get Gastronomic Specialties endpoint -----------------------------------

 @GetMapping("")
    public ResponseEntity<Object> getGastronomicSpecialties(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<GastronomicSpecialties> gastronomicSpecialtiesPage = gastronomicSpecialtiesService.getGastronomicSpecialties(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(gastronomicSpecialtiesPage);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting gastronomic specialties ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



     // ---------------------------------- get Gastronomic Specialtie By Id endpoint -----------------------------------

 @GetMapping("/{gastronomicspecialtieId}")
 public ResponseEntity<Object> getGastronomicSpecialtieById( @PathVariable(value = "gastronomicspecialtieId") Long gastronomicspecialtieId  ) {
    try{
        GastronomicSpecialties gastronomicSpecialtie = gastronomicSpecialtiesService.getGastronomicSpecialtieById(gastronomicspecialtieId);
    return ResponseEntity.status(HttpStatus.OK).body(gastronomicSpecialtie);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting gastronomic specialties with this id :"+ gastronomicspecialtieId, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }





 // ---------------------------------- delete Gastronomic Specialtie endpoint -----------------------------------

 @DeleteMapping("/{gastronomicspecialtieId}/delete")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('DELETE_PRIVILEGE')")
 public ResponseEntity<Object> deleteGastronomicSpecialtie(@PathVariable(value = "gastronomicspecialtieId") Long gastronomicspecialtieId){
    try{
     String resMessege = gastronomicSpecialtiesService.deleteGastronomicSpecialtie(gastronomicspecialtieId);
     return ResponseEntity.status(HttpStatus.ACCEPTED).body(resMessege);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while deleting gastronomic specialtie with this id :"+ gastronomicspecialtieId, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
 }

 
}
