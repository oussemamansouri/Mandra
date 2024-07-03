package com.elife.mandra.Web.RestControllers;

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
    @PreAuthorize("hasAnyRole('Owner') and hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<?> addGuestHouse(@PathVariable Long ownerId,
            @Valid @RequestPart("guesthouseForm") GuesthouseForm guesthouseForm,
            @RequestPart("images") List<MultipartFile> images,
            BindingResult result) {
        try {    
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            Guesthouse addedGuestHouse = guesthouseService.addGuestHouse(ownerId, guesthouseForm, images);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedGuestHouse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while adding guest house", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




     // ---------------------------------- update restaurant endpoint -----------------------------------

    @PutMapping("/{guesthouseId}/edit")
    @PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateGuestHouse(@PathVariable(value = "guesthouseId") Long guesthouseId,
            @Valid @RequestBody GuesthouseForm guesthouseForm,
            BindingResult result) {     
        try {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
            Guesthouse updatedGuesthouse = guesthouseService.updateGuestHouse(guesthouseId, guesthouseForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedGuesthouse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while updating guest house", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




    // ----------------------------------      update Guest House images endpoint     -----------------------------------   

 @PutMapping("/{guesthouseId}/edit-images")
 @PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('UPDATE_PRIVILEGE')")
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
    public ResponseEntity<Object> getGuestHouses(@RequestParam(required = false) String searchTerm, @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<Guesthouse> guestHousePage = guesthouseService.getGuestHouses(searchTerm, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(guestHousePage);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while getting guest houses ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





    // ----------------------------------      get Guest House By Id endpoint     -----------------------------------

 @GetMapping("/{guesthouseId}")
 public ResponseEntity<Object> getGuestHouseById( @PathVariable(value = "guesthouseId") Long guesthouseId  ) {
    try{
    Guesthouse guesthouse= guesthouseService.getGuestHousesById(guesthouseId);
    return ResponseEntity.status(HttpStatus.OK).body(guesthouse);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting guest house with this id :"+ guesthouseId, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }




 // ----------------------------------      delete guest house endpoint     -----------------------------------

 @DeleteMapping("/{guesthouseId}/delete")
 @PreAuthorize("hasAnyRole('Admin','Owner') and hasAuthority('DELETE_PRIVILEGE')")
 public ResponseEntity<Object> deleteGuestHouse(@PathVariable(value = "guesthouseId") Long guesthouseId){
    try{
     String resMessege = guesthouseService.deleteGuestHouse(guesthouseId);
     return ResponseEntity.status(HttpStatus.ACCEPTED).body(resMessege);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while deleting restaurant with this id :"+ guesthouseId, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
 }

}
