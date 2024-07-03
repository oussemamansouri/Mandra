package com.elife.mandra.DAO.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Guesthouse;


@Repository
public interface GuestHouseRepository extends JpaRepository<Guesthouse,Long> {


     @Query("SELECT g FROM Guesthouse g WHERE " +
            "LOWER(g.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(g.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(g.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(g.address) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Guesthouse> searchGuestHouses(@Param("searchTerm") String searchTerm, Pageable pageable);

}
