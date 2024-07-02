package com.elife.mandra.Web.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.AdminService;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }   



// ----------------------------------      get admin by id endpoint     -----------------------------------

 @GetMapping("/{id}")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
 public ResponseEntity<Object> getAdmin( @PathVariable(value = "id") Long id  ) {
    try{
    Admin admin= adminService.getAdminById(id);
    return ResponseEntity.status(HttpStatus.OK).body(admin);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting admin", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }
 



// ----------------------------------      update admin endpoint     -----------------------------------

 @PutMapping("/{id}/edit")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateAdmin(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdateUserForm admin,
    BindingResult result) {
    try{
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Admin updatedAdmin = adminService.updateAdmin(id, admin);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedAdmin);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating admin", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}





// ----------------------------------      edit image endpoint     -----------------------------------

   @PutMapping("/{id}/edit-image")
   @PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateAdminImage(@PathVariable(value = "id") Long id, 
                                                    @RequestParam("image") MultipartFile image) {
        try {
            Admin updatedAdmin = adminService.updateAdminImage(id, image);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedAdmin);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while updating admin image", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




// ----------------------------------      update admin Password endpoint     -----------------------------------

@PutMapping("/{id}/edit-password")
@PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateAdminPasswoed(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdatePasswordForm form,
    BindingResult result) {
    try{    
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Admin updatedAdminPassword = adminService.updateAdminPassword(form, id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedAdminPassword);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating admin password", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}






}
