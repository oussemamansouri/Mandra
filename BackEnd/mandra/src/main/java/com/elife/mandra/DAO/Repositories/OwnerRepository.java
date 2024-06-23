package com.elife.mandra.DAO.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elife.mandra.DAO.Entities.Owner;
import java.util.Optional;

import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;


@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long>{

    public Optional<Owner> findByEmail(String email);

    public Page<Owner> findByAccountState(AccountStateOption accountState, Pageable pageable);

}
