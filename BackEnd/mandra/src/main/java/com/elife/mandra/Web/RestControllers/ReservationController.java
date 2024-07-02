package com.elife.mandra.Web.RestControllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    
    // ---------------------------------- Get All Reservations endpoint -----------------------------------

    @GetMapping("")
    public ResponseEntity<Object> getAllReservations(@PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<Reservation> reservationPage = reservationService.getAllReservations(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(reservationPage);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while getting all reservations", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    // ---------------------------------- Get Reservation By Id endpoint -----------------------------------

    @GetMapping("/{reservationId}")
    public ResponseEntity<Object> getReservationById(@PathVariable(value = "reservationId") Long reservationId) {
        try {
            Reservation reservation = reservationService.getReservationById(reservationId);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while getting reservation with id: " + reservationId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

 // ---------------------------------- Delete Reservation endpoint -----------------------------------

 @DeleteMapping("/{reservationId}/delete")
 public ResponseEntity<Object> deleteReservation(@PathVariable(value = "reservationId") Long reservationId) {
     try {
         String message = reservationService.deleteReservation(reservationId);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
     } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while deleting reservation with id: " + reservationId, e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
     }
 }

  // ---------------------------------- Get Reservations By Hotel endpoint -----------------------------------

  @GetMapping("/hotel/{hotelName}")
  public ResponseEntity<Object> getReservationsByHotel(
          @PathVariable(value = "hotelName") String hotelName,
          @PageableDefault(size = 10) Pageable pageable) {
      try {
          Page<Reservation> reservationPage = reservationService.getReservationsByHotel(hotelName, pageable);
          return ResponseEntity.status(HttpStatus.OK).body(reservationPage);
      } catch (Exception e) {
          ErrorResponse errorResponse = new ErrorResponse("Error while getting reservations for hotel: " + hotelName, e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
      }
  }

   // ---------------------------------- Get Reservations By Client endpoint -----------------------------------

   @GetMapping("/client/{clientName}")
   public ResponseEntity<Object> getReservationsByClient(
           @PathVariable(value = "clientName") String clientName,
           @PageableDefault(size = 10) Pageable pageable) {
       try {
           Page<Reservation> reservationPage = reservationService.getReservationsByClient(clientName, pageable);
           return ResponseEntity.status(HttpStatus.OK).body(reservationPage);
       } catch (Exception e) {
           ErrorResponse errorResponse = new ErrorResponse("Error while getting reservations for client: " + clientName, e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
       }
   }
}