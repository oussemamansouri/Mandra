package com.elife.mandra.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elife.mandra.DAO.Entities.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
