package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elife.mandra.DAO.Entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
