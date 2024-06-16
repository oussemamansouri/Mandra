package com.elife.mandra.Web.RestControllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.elife.mandra.Business.Services.GuesthouseService;
import com.elife.mandra.DAO.Entities.Guesthouse;
import com.elife.mandra.Web.Requests.PropertyForms.GuesthouseForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

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




     // ---------------------------------- update restaurant endpoint -----------------------------------

    @PutMapping("/{guesthouseId}/edit")
    public ResponseEntity<Object> updateGuestHouse(@PathVariable(value = "guesthouseId") Long guesthouseId,
            @Valid @RequestBody GuesthouseForm guesthouseForm,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            Guesthouse updatedGuesthouse = guesthouseService.updateGuestHouse(guesthouseId, guesthouseForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedGuesthouse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




    // ----------------------------------      update Guest House images endpoint     -----------------------------------   

 @PutMapping("/{guesthouseId}/edit-images")
 public ResponseEntity<Object> updateGuestHouseImages(@PathVariable(value = "guesthouseId") Long guesthouseId, 
                                                 @RequestParam("images") List<MultipartFile> images) {
     try {
         Guesthouse updatedGuesthouse = guesthouseService.updateGuestHouseImage(guesthouseId, images);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedGuesthouse);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while uploading guest house images", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }




    // ----------------------------------      get Guest Houses endpoint     -----------------------------------

 @GetMapping("")
    public ResponseEntity<Object> getGuestHouses(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<Guesthouse> guestHouse = guesthouseService.getGuestHouses(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(guestHouse);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting guest houses ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
