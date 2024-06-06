package com.elife.mandra.Business.ServicesImp;

import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Repositories.OwnerRepository;

@Service
public class OwnerServiceImp implements OwnerService {


    final OwnerRepository ownerRepository;
    public OwnerServiceImp(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }


    @Override
    public Owner addOwner(Owner owner) {
        try{
          return this.ownerRepository.save(owner); 
        }catch(Exception e){
         throw new RuntimeException("Failed to save owner: " + e.getMessage(), e);
        }
    }

    @Override
    public Owner getOwners() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOwners'");
    }

    @Override
    public Owner getOwnerById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOwnerById'");
    }

    @Override
    public Owner updateOwner(Owner owner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwner'");
    }

    @Override
    public Owner updateOwnerImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwnerImage'");
    }

    @Override
    public Owner updateOwnerPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwnerPassword'");
    }

    @Override
    public void deleteOwner(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOwner'");
    }

}
