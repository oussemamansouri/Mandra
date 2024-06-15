package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.HotelService;
import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

import java.util.List;

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
@RequestMapping("/hotels")
public class HotelController {

    final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }



 // ----------------------------------      add Hotel by owner endpoint     -----------------------------------   

    @PostMapping("/{ownerId}/add")
    public ResponseEntity<?> addHotel(@PathVariable Long ownerId,
                                      @Valid @RequestPart("hotelForm") HotelForm hotelForm,
                                      @RequestPart("images") List<MultipartFile> images,
                                      BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            Hotel addedHotel = hotelService.addHotel(ownerId, hotelForm, images);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }





 // ----------------------------------      update hotel endpoint     -----------------------------------

@PutMapping("/{hotelId}/edit")
    public ResponseEntity<Object> updateHotel(@PathVariable(value = "hotelId") Long hotelId,@Valid @RequestBody HotelForm hotelForm,
    BindingResult result) {
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    try{
    Hotel updatedHotel = hotelService.updateHotel(hotelId, hotelForm);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedHotel);
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}




 // ----------------------------------      update Hotel images endpoint     -----------------------------------   

 @PutMapping("/{hotelId}/edit-images")
 public ResponseEntity<Object> updateHotelImages(@PathVariable(value = "hotelId") Long hotelId, 
                                                 @RequestParam("images") List<MultipartFile> images) {
     try {
         Hotel updatedHotel = hotelService.updateHotelImages(hotelId, images);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedHotel);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while uploading hotel images", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }



}
