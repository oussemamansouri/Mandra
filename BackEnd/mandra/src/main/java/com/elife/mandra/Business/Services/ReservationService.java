package com.elife.mandra.Business.Services;

import com.elife.mandra.DAO.Entities.Reservation;
import com.elife.mandra.Web.Requests.ReservationForms.ReservationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {

    // Create Operation
    Reservation createReservation(ReservationForm reservationForm);

    // Read Operations
    Page<Reservation> getAllReservations(Pageable pageable);
    Reservation getReservationById(Long reservationId);
    Page<Reservation> getReservationsByHotel(String hotelName, Pageable pageable);
    Page<Reservation> getReservationsByClient(String clientName, Pageable pageable);

    
    String deleteReservation(Long reservationId);
}
