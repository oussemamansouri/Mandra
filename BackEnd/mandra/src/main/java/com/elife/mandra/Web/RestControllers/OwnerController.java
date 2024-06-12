package com.elife.mandra.Web.RestControllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.Web.Requests.OwnerForms.AddOwnerForm;

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
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Owner registeredOwner = ownerService.registerOwner(ownerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredOwner);
    }





    // ----------------------------------      get Owners endpoint     -----------------------------------

 @GetMapping("")
    public ResponseEntity<Object> getOwners(@PageableDefault(size = 10) Pageable pageable) {
       Page<Owner> owner = ownerService.getOwners(pageable);
      return ResponseEntity.status(HttpStatus.OK).body(owner);
    }

}
