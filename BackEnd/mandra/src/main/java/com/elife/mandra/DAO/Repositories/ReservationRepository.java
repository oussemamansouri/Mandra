package com.elife.mandra.DAO.Repositories;

import com.elife.mandra.DAO.Entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByHotelId(String hotelName, Pageable pageable);
    Page<Reservation> findByClientId(String clientName, Pageable pageable);
}
