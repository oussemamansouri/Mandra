package com.elife.mandra.DAO.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

     @Query("SELECT r FROM Restaurant r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(r.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(r.address) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Restaurant> searchRestaurants(@Param("searchTerm") String searchTerm, Pageable pageable);

}
