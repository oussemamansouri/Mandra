package com.elife.mandra.Web.RestControllers;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.Web.Requests.OwnerForms.AddOwnerForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    final OwnerService ownerService;
 public OwnerController(OwnerService ownerService){
    this.ownerService = ownerService;
 }   



// ----------------------------------      register Owner endpoint     -----------------------------------

@PostMapping("/register")
    public ResponseEntity<?> registerOwner(@Valid @RequestBody AddOwnerForm ownerForm, BindingResult result) {
        try{
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Owner registeredOwner = ownerService.registerOwner(ownerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredOwner);
        }catch(RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse("Error while register owner", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





    // ----------------------------------      get Owners endpoint     -----------------------------------

 @GetMapping("")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Object> getOwners(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<Owner> ownerPage = ownerService.getOwners(pageable);
      return ResponseEntity.status(HttpStatus.OK).body(ownerPage);
        }catch(RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting owners", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }







     // ----------------------------------      get Owner By Id endpoint     -----------------------------------

 @GetMapping("/{id}")
 @PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('READ_PRIVILEGE')")
 public ResponseEntity<Object> getOwnerById( @PathVariable(value = "id") Long id  ) {
    try{
    Owner owner= ownerService.getOwnerById(id);
    return ResponseEntity.status(HttpStatus.OK).body(owner);
    }catch(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting owner by this id :" + id, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }





 // ----------------------------------      upload owner cin image endpoint     -----------------------------------

   @PutMapping("/{id}/upload-cin-image")
    public ResponseEntity<Object> updateOwnerCinImage(@PathVariable(value = "id") Long id, 
                                                    @RequestParam("cinImage") MultipartFile image) {
        try {
            Owner updatedOwner = ownerService.uploadCinImage(id, image);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while uploading owner cin image", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





     // ----------------------------------      upload owner proof file endpoint     -----------------------------------

   @PutMapping("/{id}/upload-proof")
   public ResponseEntity<Object> updateOwnerProof(@PathVariable(value = "id") Long id, 
                                                   @RequestParam("proof") MultipartFile file) {
       try {
           Owner updatedOwner = ownerService.uploadProofFile(id, file);
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
       } catch (RuntimeException e) {
           ErrorResponse errorResponse = new ErrorResponse("Error while uploading owner proof", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
       }
   }





   // ----------------------------------      update Owner endpoint     -----------------------------------

@PutMapping("/{id}/edit")
@PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateOwner(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdateUserForm ownerForm,
    BindingResult result) {
    try{
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Owner updatedOwner = ownerService.updateOwner(id, ownerForm);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
    }catch(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating owner", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}





 // ----------------------------------      upload owner image endpoint     -----------------------------------

 @PutMapping("/{id}/edit-image")
 @PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
 public ResponseEntity<Object> updateOwnerImage(@PathVariable(value = "id") Long id, 
                                                 @RequestParam("image") MultipartFile image) {
     try {
         Owner updatedOwner = ownerService.updateOwnerImage(id, image);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while uploading owner image", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }





 // ----------------------------------      update Owner Password endpoint     -----------------------------------

@PutMapping("/{id}/edit-password")
@PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateOwnerPasswoed(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdatePasswordForm form,
    BindingResult result) {
    try{
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Owner updatedOwnerPassword = ownerService.updateOwnerPassword(form, id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwnerPassword);
    }catch(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating owner password", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}





// ----------------------------------      delete Owner endpoint     -----------------------------------

 @DeleteMapping("/{id}/delete")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('DELETE_PRIVILEGE')")
 public ResponseEntity<Object> deleteOwner(@PathVariable Long id) {
     try {
         String resMessage = ownerService.deleteOwner(id);
         return ResponseEntity.status(HttpStatus.OK).body(resMessage);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while deleting owner", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }




 

    // ---------------------------------- get Active Owners endpoint -----------------------------------

     @GetMapping("/active")
     @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
     public ResponseEntity<Object> getActiveOwners(@PageableDefault(size = 10) Pageable pageable) {
        try{
        Page<Owner> ownerPage = ownerService.getActiveOwners(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ownerPage);
        }catch(RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting active owners", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
     }





    // ---------------------------------- get Disabled Owners endpoint -----------------------------------

    @GetMapping("/disabled")
    @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Object> getDisabledOwners(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<Owner> ownerPage = ownerService.getDisabledOwners(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(ownerPage); 
        }catch(RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting Disabled owners", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
     }




    
    // ---------------------------------- change Owner Account State endpoint -----------------------------------

    @PutMapping("/{ownerId}/change-account-state")
    @PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> changeOwnerAccountState(@PathVariable(value = "ownerId") Long ownerId ) {
    try{
    Owner updatedOwner = ownerService.changeOwnerAccountState(ownerId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
    }catch(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse("Error while changing Owner Account State", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    }   

}
