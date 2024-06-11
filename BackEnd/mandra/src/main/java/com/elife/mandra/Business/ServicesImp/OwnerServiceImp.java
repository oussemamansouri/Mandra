package com.elife.mandra.Business.ServicesImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.OwnerForms.AddOwnerForm;

@Service
public class OwnerServiceImp implements OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImp.class);

    final OwnerRepository ownerRepository;
    public OwnerServiceImp(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

       @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


// ----------------------------------      register Owner     -----------------------------------

    @Override
    public Owner registerOwner(AddOwnerForm ownerForm) {
         try {
            List<Owner> nbOwners = ownerRepository.findByEmail(ownerForm.getEmail());
            if (nbOwners.isEmpty()) {
                ownerForm.setPassword(bCryptPasswordEncoder.encode(ownerForm.getPassword()));
                Owner newOwner = new Owner(
                    ownerForm.getFirstname(),
                    ownerForm.getLastname(),
                    ownerForm.getEmail(),
                    ownerForm.getPassword(),
                    ownerForm.getPhoneNumber(),
                    ownerForm.getProof(),
                    ownerForm.getCinImage(),
                    ownerForm.getNbOfHotels(),
                    ownerForm.getNbOfRestaurant(),
                    ownerForm.getNbOfGuesthouses(),
                    RoleOption.Owner,
                    null, // image
                    AccountStateOption.Diactive
                );
                return ownerRepository.save(newOwner);
            } else {
                throw new RuntimeException("This email is already in use!");
            }
        } catch (Exception e) {
            LOGGER.error("Error while registering owner", e);
            throw new RuntimeException("Error while registering owner: " + e.getMessage(), e);
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
