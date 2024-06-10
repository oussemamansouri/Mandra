package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Owner;
import java.util.List;



@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long>{

    public List<Owner> findByEmail(String email);

}
