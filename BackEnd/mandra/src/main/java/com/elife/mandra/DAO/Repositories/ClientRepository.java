package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Client;
import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    public List<Client> findByEmail(String email);

}
