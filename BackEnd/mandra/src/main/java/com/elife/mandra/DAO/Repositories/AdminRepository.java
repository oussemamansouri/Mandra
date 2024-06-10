package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Admin;
import java.util.List;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

      public List<Admin> findByEmail(String email);

}
