package com.elife.mandra.Business.ServicesImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.ReservationService;
import com.elife.mandra.DAO.Entities.Reservation;
import com.elife.mandra.DAO.Repositories.ReservationRepository;
import com.elife.mandra.Web.Requests.ReservationForms.ReservationForm;

@Service
public class ReservationServiceImp implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImp.class);

    final ReservationRepository reservationRepository;

    public ReservationServiceImp(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // ---------------------------------- Create Reservation -----------------------------------

    @Override
    public Reservation createReservation(ReservationForm reservationForm) {
        try {
            // Construct your Reservation entity here
            Reservation newReservation = new Reservation(
                reservationForm.getHotelName(),
                reservationForm.getClientName(),
                reservationForm.getCheckInDate(),
                reservationForm.getCheckOutDate(),
                reservationForm.getNumOfAdults(),
                reservationForm.getNumOfChildren(),
                reservationForm.getNumOfRooms()
            );

            return reservationRepository.save(newReservation);

        } catch (Exception e) {
            LOGGER.error("Error while creating reservation", e);
            throw new RuntimeException("Error while creating reservation: " + e.getMessage(), e);
        }
    }

    // ---------------------------------- Get All Reservations -----------------------------------

    @Override
    public Page<Reservation> getAllReservations(Pageable pageable) {
        try {
            return reservationRepository.findAll(pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding reservations", e);
            throw new RuntimeException("Failed to find reservations: " + e.getMessage(), e);
        }
    }

    // ---------------------------------- Get Reservation By Id -----------------------------------

    @Override
    public Reservation getReservationById(Long reservationId) {
        try {
            return reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));
        } catch (Exception e) {
            LOGGER.error("Error while getting reservation with id: " + reservationId, e);
            throw new RuntimeException("Failed to find reservation with id: " + reservationId + ": " + e.getMessage(), e);
        }
    }

    // ---------------------------------- Get Reservations By Hotel -----------------------------------

    @Override
    public Page<Reservation> getReservationsByHotel(String hotelName, Pageable pageable) {
        try {
            return reservationRepository.findByHotelId(hotelName, pageable);
        } catch (Exception e) {
            LOGGER.error("Error while getting reservations by hotel: " + hotelName, e);
            throw new RuntimeException("Failed to find reservations by hotel: " + hotelName + ": " + e.getMessage(), e);
        }
    }

    // ---------------------------------- Get Reservations By Client -----------------------------------

    @Override
    public Page<Reservation> getReservationsByClient(String clientName, Pageable pageable) {
        try {
            return reservationRepository.findByClientId(clientName, pageable);
        } catch (Exception e) {
            LOGGER.error("Error while getting reservations by client: " + clientName, e);
            throw new RuntimeException("Failed to find reservations by client: " + clientName + ": " + e.getMessage(), e);
        }
    }

    // ---------------------------------- Delete Reservation -----------------------------------

    @Override
    public String deleteReservation(Long reservationId) {
        try {
            if (!reservationRepository.existsById(reservationId)) {
                throw new RuntimeException("Reservation not found with id: " + reservationId);
            }
            reservationRepository.deleteById(reservationId);
            return "Reservation deleted successfully";
        } catch (Exception e) {
            LOGGER.error("Error while deleting reservation with id: " + reservationId, e);
            throw new RuntimeException("Error while deleting reservation with id: " + reservationId + ": " + e.getMessage(), e);
        }
    }
}
