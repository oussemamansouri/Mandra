package com.elife.mandra.Web.RestControllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.GuesthouseService;
import com.elife.mandra.DAO.Entities.Guesthouse;
import com.elife.mandra.Web.Requests.PropertyForms.GuesthouseForm;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/guesthouses")
public class GuesthouseController {

    final GuesthouseService guesthouseService;

    public GuesthouseController(GuesthouseService guesthouseService) {
        this.guesthouseService = guesthouseService;
    }



      // ---------------------------------- add Guest House by owner endpoint -----------------------------------

    @PostMapping("/{ownerId}/add")
    public ResponseEntity<?> addGuestHouse(@PathVariable Long ownerId,
            @Valid @RequestPart("guesthouseForm") GuesthouseForm guesthouseForm,
            @RequestPart("images") List<MultipartFile> images,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            Guesthouse addedGuestHouse = guesthouseService.addGuestHouse(ownerId, guesthouseForm, images);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedGuestHouse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
