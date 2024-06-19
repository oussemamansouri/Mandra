package com.elife.mandra.Web.RestControllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.SpecialtyWomenService;
import com.elife.mandra.DAO.Entities.SpecialtyWomen;
import com.elife.mandra.Web.Requests.SpecialtyWomenForms.SpecialtyWomenForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/specialtywomens")
public class SpecialtyWomenController {

     final SpecialtyWomenService specialtyWomenService ;

    public SpecialtyWomenController(SpecialtyWomenService specialtyWomenService) {
        this.specialtyWomenService = specialtyWomenService;
    }




    // ---------------------------------- add Specialty Women by admin endpoint -----------------------------------

    @PostMapping("/{adminId}/add")
    public ResponseEntity<?> addGastronomicSpecialtie(@PathVariable Long adminId,
            @Valid @RequestPart("specialtywomenform") SpecialtyWomenForm specialtywomenform,
            @RequestPart("image") MultipartFile image,
            BindingResult result) {
        try {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            SpecialtyWomen addedSpecialtyWomen = specialtyWomenService
            .addSpecialtyWomenForm(adminId, specialtywomenform, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedSpecialtyWomen);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while adding specialty women ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





    // ---------------------------------- update Specialty Women endpoint -----------------------------------

    @PutMapping("/{specialtyWomenId}/edit")
    public ResponseEntity<Object> updateSpecialtyWomen(@PathVariable(value = "specialtyWomenId") Long specialtyWomenId,
            @Valid @RequestBody SpecialtyWomenForm specialtyWomenForm,
            BindingResult result) {
        try {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            SpecialtyWomen updatedSpecialtyWomen = specialtyWomenService.updateSpecialtyWomen(specialtyWomenId, specialtyWomenForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedSpecialtyWomen);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while updating specialty women", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





      // ---------------------------------- edit Specialty Women image endpoint -----------------------------------

   @PutMapping("/{specialtyWomenId}/edit-image")
   public ResponseEntity<Object> updateSpecialtyWomen(@PathVariable(value = "specialtyWomenId") Long specialtyWomenId, 
                                                   @RequestParam("image") MultipartFile image) {
       try {
        SpecialtyWomen updatedspecialtyWomen = specialtyWomenService
           .updateSpecialtyWomenImage(specialtyWomenId, image);

           return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedspecialtyWomen);
       } catch (RuntimeException e) {
           ErrorResponse errorResponse = new ErrorResponse("Error while updating specialty women image", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
       }
   }




    // ---------------------------------- get Specialty Women endpoint -----------------------------------

 @GetMapping("")
    public ResponseEntity<Object> getSpecialtyWomens(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<SpecialtyWomen> specialtyWomenPage = specialtyWomenService.getSpecialtyWomens(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(specialtyWomenPage);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting specialty womens ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





    // ---------------------------------- get Specialty Women By Id endpoint -----------------------------------

 @GetMapping("/{specialtyWomenId}")
 public ResponseEntity<Object> getSpecialtyWomenById( @PathVariable(value = "specialtyWomenId") Long specialtyWomenId  ) {
    try{
        SpecialtyWomen specialtyWomen = specialtyWomenService.getSpecialtyWomenById(specialtyWomenId);
    return ResponseEntity.status(HttpStatus.OK).body(specialtyWomen);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting specialty women with this id :"+ specialtyWomenId, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }




 
 // ---------------------------------- delete Specialty Women endpoint -----------------------------------

 @DeleteMapping("/{specialtyWomenId}/delete")
 public ResponseEntity<Object> deleteSpecialtyWomen(@PathVariable(value = "specialtyWomenId") Long specialtyWomenId){
    try{
     String resMessege = specialtyWomenService.deleteSpecialtyWomen(specialtyWomenId);
     return ResponseEntity.status(HttpStatus.ACCEPTED).body(resMessege);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while deleting specialty women with this id :"+ specialtyWomenId, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
 }



}
