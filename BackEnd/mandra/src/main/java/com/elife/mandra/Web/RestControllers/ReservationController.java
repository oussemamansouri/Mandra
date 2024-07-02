package com.elife.mandra.Web.RestControllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.elife.mandra.Business.Services.ReservationService;
import com.elife.mandra.DAO.Entities.Reservation;
import com.elife.mandra.Web.Requests.ReservationForms.ReservationForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // ---------------------------------- Create Reservation endpoint -----------------------------------

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(
            @Valid @RequestBody ReservationForm reservationForm,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
                return ResponseEntity.badRequest().body(errors.toString());
            }

            Reservation createdReservation = reservationService.createReservation(reservationForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while creating reservation", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}