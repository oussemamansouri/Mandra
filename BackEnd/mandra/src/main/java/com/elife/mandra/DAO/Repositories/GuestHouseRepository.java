package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Guesthouse;


@Repository
public interface GuestHouseRepository extends JpaRepository<Guesthouse,Long> {

}
