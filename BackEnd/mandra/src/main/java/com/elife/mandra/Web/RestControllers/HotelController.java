package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.HotelService;
import com.elife.mandra.DAO.Entities.Hotel;
import com.elife.mandra.Web.Requests.PropertyForms.HotelForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

import java.util.List;

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
@RequestMapping("/hotels")
public class HotelController {

    final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }



 // ----------------------------------      add Hotel by owner endpoint     -----------------------------------   

    @PostMapping("/{ownerId}/add")
    @PreAuthorize("hasAnyRole('Owner') and hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<?> addHotel(@PathVariable Long ownerId,
                                      @Valid @RequestPart("hotelForm") HotelForm hotelForm,
                                      @RequestPart("images") List<MultipartFile> images,
                                      BindingResult result) {
        try {                              
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            Hotel addedHotel = hotelService.addHotel(ownerId, hotelForm, images);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedHotel);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while adding hotel", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





 // ----------------------------------      update hotel endpoint     -----------------------------------

@PutMapping("/{hotelId}/edit")
@PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateHotel(@PathVariable(value = "hotelId") Long hotelId,@Valid @RequestBody HotelForm hotelForm,
    BindingResult result) {
    try {
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Hotel updatedHotel = hotelService.updateHotel(hotelId, hotelForm);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedHotel);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating hotel", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}




 // ----------------------------------      update Hotel images endpoint     -----------------------------------   

 @PutMapping("/{hotelId}/edit-images")
 @PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
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




    // ----------------------------------      get Hotels endpoint     -----------------------------------

 @GetMapping("")
    public ResponseEntity<Object> getHotels(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<Hotel> hotelPage = hotelService.getHotels(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(hotelPage);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting hotels ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    // ----------------------------------      get Hotel By Id endpoint     -----------------------------------

 @GetMapping("/{hotelId}")
 public ResponseEntity<Object> getHotelById( @PathVariable(value = "hotelId") Long hotelId  ) {
    try{
    Hotel hotel= hotelService.getHotelById(hotelId);
    return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting hotel with this id :"+hotelId, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }


 
// ----------------------------------      delete hotel endpoint     -----------------------------------

 @DeleteMapping("/{hotelIdid}/delete")
@PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('DELETE_PRIVILEGE')")
 public ResponseEntity<Object> deleteHotel(@PathVariable Long hotelIdid) {
     try {
         String resMessage = hotelService.deleteHotel(hotelIdid);
         return ResponseEntity.status(HttpStatus.OK).body(resMessage);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while deleting hotel", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }


}
