package com.elife.mandra.Business.Services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.Web.Requests.OwnerForms.AddOwnerForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

public interface OwnerService {

    //Create operation
    public Owner registerOwner(AddOwnerForm ownerForm);

    //Read operation
    public Page<Owner> getOwners(Pageable pageable);
    public Owner getOwnerById(Long id);
    public Page<Owner> getActiveOwners(Pageable pageable);
    public Page<Owner> getDisabledOwners(Pageable pageable);
 
    //Update operation
    public Owner uploadCinImage(Long id, MultipartFile cinImage);
    public Owner uploadProofFile(Long id, MultipartFile proofFile);
    public Owner updateOwner(Long id, UpdateUserForm ownerForm);
    public Owner updateOwnerImage(Long id, MultipartFile image);
    public Owner updateOwnerPassword(UpdatePasswordForm form, Long id);

    //Delete operation
    public String deleteOwner(Long id); 

}
