package com.elife.mandra.DAO.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public List<Client> findByEmail(String email);

    public Page<Client> findByAccountState(AccountStateOption accountState, Pageable pageable);

}
