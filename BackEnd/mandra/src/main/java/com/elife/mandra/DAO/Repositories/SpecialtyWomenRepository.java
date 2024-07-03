package com.elife.mandra.DAO.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.SpecialtyWomen;


@Repository
public interface SpecialtyWomenRepository extends JpaRepository<SpecialtyWomen,Long> {

     @Query("SELECT s FROM SpecialtyWomen s WHERE " +
            "LOWER(s.specialty) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(s.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(s.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(s.address) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<SpecialtyWomen> searchSpecialtyWomens(@Param("searchTerm") String searchTerm, Pageable pageable);

}
