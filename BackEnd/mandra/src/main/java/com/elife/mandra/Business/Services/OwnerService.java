package com.elife.mandra.Business.Services;

import com.elife.mandra.DAO.Entities.Owner;

public interface OwnerService {

    //Create operation
    public Owner registerOwner(Owner owner);

    //Read operation
    public Owner getOwners();
    public Owner getOwnerById(Long id);
 
    //Update operation
    public Owner updateOwner(Owner owner);
    public Owner updateOwnerImage();
    public Owner updateOwnerPassword();

    //Delete operation
    public void deleteOwner(Long id); 

}
