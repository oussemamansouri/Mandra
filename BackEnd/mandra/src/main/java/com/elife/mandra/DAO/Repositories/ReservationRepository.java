package com.elife.mandra.DAO.Repositories;

import com.elife.mandra.DAO.Entities.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByHotelId(Long hotelId);
    List<Reservation> findByClientId(Long clientId);
    List<Reservation> findByOwnerId(Long ownertId);
}

