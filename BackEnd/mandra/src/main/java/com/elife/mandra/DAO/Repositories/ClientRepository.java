package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elife.mandra.DAO.Entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
