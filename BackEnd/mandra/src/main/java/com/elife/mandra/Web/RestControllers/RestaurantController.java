package com.elife.mandra.Web.RestControllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.RestaurantService;
import com.elife.mandra.DAO.Entities.Restaurant;
import com.elife.mandra.Web.Requests.PropertyForms.RestaurantForm;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // ---------------------------------- add Restaurant by owner endpoint -----------------------------------

    @PostMapping("/{ownerId}/add")
    public ResponseEntity<?> addRestaurant(@PathVariable Long ownerId,
            @Valid @RequestPart("restaurantForm") RestaurantForm restaurantForm,
            @RequestPart("images") List<MultipartFile> images,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            Restaurant addedRestaurant = restaurantService.addReataurant(ownerId, restaurantForm, images);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedRestaurant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // ---------------------------------- update restaurant endpoint -----------------------------------

    @PutMapping("/{restaurantId}/edit")
    public ResponseEntity<Object> updateRestaurant(@PathVariable(value = "restaurantId") Long restaurantId,
            @Valid @RequestBody RestaurantForm restaurantForm,
            BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        try {
            Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, restaurantForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedRestaurant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
