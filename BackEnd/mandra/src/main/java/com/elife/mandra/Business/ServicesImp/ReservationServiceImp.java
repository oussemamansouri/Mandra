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

    @Override
    public Page<Reservation> getAllReservations(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReservations'");
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationById'");
    }

    @Override
    public Page<Reservation> getReservationsByHotel(String hotelName, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationsByHotel'");
    }

    @Override
    public Page<Reservation> getReservationsByClient(String clientName, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationsByClient'");
    }

    @Override
    public String deleteReservation(Long reservationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReservation'");
    }
}