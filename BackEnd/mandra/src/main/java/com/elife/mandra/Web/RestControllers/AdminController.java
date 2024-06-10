package com.elife.mandra.Web.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.AdminService;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.Web.Requests.UserForms.AddUserForm;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }   

// ----------------------------------      addAdmin endpoint     -----------------------------------

@PostMapping("/add")
    public ResponseEntity<?> addingAdmin(@Valid @RequestBody AddUserForm admin, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Admin addedClient = adminService.addAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedClient);
    }






}
