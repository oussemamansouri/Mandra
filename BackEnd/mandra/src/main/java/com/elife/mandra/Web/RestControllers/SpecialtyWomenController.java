package com.elife.mandra.Web.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.SpecialtyWomenService;
import com.elife.mandra.DAO.Entities.SpecialtyWomen;
import com.elife.mandra.Web.Requests.SpecialtyWomenForms.SpecialtyWomenForm;

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
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            SpecialtyWomen addedSpecialtyWomen = specialtyWomenService
            .addSpecialtyWomenForm(adminId, specialtywomenform, image);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(addedSpecialtyWomen);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
