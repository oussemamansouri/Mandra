package com.elife.mandra.DAO.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long>{

     @Query("SELECT h FROM Hotel h WHERE " +
            "LOWER(h.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(h.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(h.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(h.address) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Hotel> searchHotels(@Param("searchTerm") String searchTerm, Pageable pageable);

}
