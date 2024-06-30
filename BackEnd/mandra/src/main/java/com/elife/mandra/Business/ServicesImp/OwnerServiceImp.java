package com.elife.mandra.Business.ServicesImp;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.AdminRepository;
import com.elife.mandra.DAO.Repositories.ClientRepository;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.OwnerForms.AddOwnerForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

@Service
public class OwnerServiceImp implements OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerServiceImp.class);

   final ClientRepository clientRepository ;
    final OwnerRepository ownerRepository;
    final AdminRepository adminRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final FileService fileService;
    
    public OwnerServiceImp(ClientRepository clientRepository,
     OwnerRepository ownerRepository, AdminRepository adminRepository,
     BCryptPasswordEncoder bCryptPasswordEncoder, FileService fileService){

        this.clientRepository = clientRepository;
        this.ownerRepository = ownerRepository;
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.fileService = fileService;
    }
    //or we can 
    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    // private FileService fileService ;



// ----------------------------------      register Owner     -----------------------------------

    @Override
    public Owner registerOwner(AddOwnerForm ownerForm) {
        String email = ownerForm.getEmail();
    
        // Check if the email is already used by any user type
        boolean emailExists = clientRepository.existsByEmail(email) ||
                              ownerRepository.existsByEmail(email) ||
                              adminRepository.existsByEmail(email);
    
        if (emailExists) {
            throw new RuntimeException("This email is already in use!");
        }
         try {
                ownerForm.setPassword(bCryptPasswordEncoder.encode(ownerForm.getPassword()));
                Owner newOwner = new Owner(
                    ownerForm.getFirstname(),
                    ownerForm.getLastname(),
                    ownerForm.getEmail(),
                    ownerForm.getPassword(),
                    ownerForm.getPhoneNumber(),
                    null,
                    null,
                    ownerForm.getNbOfHotels(),
                    ownerForm.getNbOfRestaurant(),
                    ownerForm.getNbOfGuesthouses(),
                    RoleOption.Owner,
                    null, // image
                    AccountStateOption.Disabled
                );
                return ownerRepository.save(newOwner);
           
        } catch (Exception e) {
            LOGGER.error("Error while registering owner", e);
            throw new RuntimeException("Error while registering owner: " + e.getMessage(), e);
        }
    }





    // ----------------------------------      get Owners     -----------------------------------

    @Override
    public Page<Owner> getOwners(Pageable pageable) {
        try {
            return ownerRepository.findAll(pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding owners", e);
            throw new RuntimeException("Failed to find owners: " + e.getMessage(), e);
        }
    }




    // ----------------------------------      get Client By Id     -----------------------------------

    @Override
    public Owner getOwnerById(Long id) {
        try {
            return ownerRepository.findById(id).get();
        } catch (Exception e) {
            LOGGER.error("Error while finding owner with this id :" +id+" :", e);
            throw new RuntimeException("Failed to find owner with this id "+id+" : " + e.getMessage(), e);
        }
    }




    // ----------------------------------      upload Owner Cin Image     -----------------------------------

    @Override
    public Owner uploadCinImage(Long id, MultipartFile cinImage) {
       try {
             // Fetch the owner first
             Owner owner = ownerRepository.findById(id).orElse(null);
             if (owner == null) {
                 throw new RuntimeException("Failed to find owner with this id: " + id);
             }

            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(cinImage.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image type");
            }
    
            String imagePath = fileService.saveFile(cinImage); // Save the file and get the path
            owner.setCinImage(imagePath);
            return ownerRepository.save(owner);
        } catch (Exception e) {
            LOGGER.error("Error while uploading owner cin image", e);
            throw new RuntimeException("Error while uploading owner cin image : " + e.getMessage(), e);
        }
    }



    // ----------------------------------      upload Owner proof file     -----------------------------------

    @Override
    public Owner uploadProofFile(Long id, MultipartFile proofFile) {
        try {

            // Fetch the owner first
            Owner owner = ownerRepository.findById(id).orElse(null);
            if (owner == null) {
                throw new RuntimeException("Failed to find owner with this id: " + id);
            }

            // Validate if the file is an pdf
            String extension = FilenameUtils.getExtension(proofFile.getOriginalFilename());
            if (!extension.matches("pdf")) {
                throw new RuntimeException("Invalid file type");
            }
    
            String imagePath = fileService.saveFile(proofFile); // Save the file and get the path
            owner.setProof(imagePath);

            return ownerRepository.save(owner);
        } catch (Exception e) {
            LOGGER.error("Error while uploading owner proof file", e);
            throw new RuntimeException("Error while uploading owner proof file : " + e.getMessage(), e);
        }
    }

       

    // ----------------------------------      update Owner     -----------------------------------

    @Override
    public Owner updateOwner(Long id, UpdateUserForm ownerForm) {
             try {
            Owner own = ownerRepository.getReferenceById(id);
            own.setFirstname(ownerForm.getFirstname());
            own.setLastname(ownerForm.getLastname());
            own.setPhoneNumber(ownerForm.getPhoneNumber());
            return ownerRepository.save(own);
        } catch (Exception e) {
            LOGGER.error("Error while updating owner", e);
            throw new RuntimeException("Error while updating owner: " + e.getMessage(), e);
        }
    }


    // ----------------------------------      update Owner image     -----------------------------------

    @Override
    public Owner updateOwnerImage(Long id, MultipartFile image) {
        try {
            // Fetch the owner first
            Owner owner = ownerRepository.findById(id).orElse(null);
            if (owner == null) {
                throw new RuntimeException("Failed to find owner with this id: " + id);
            }
    
            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image type");
            }
    
            // Save the image file
            String imagePath = fileService.saveFile(image); // Save the file and get the path
            owner.setImage(imagePath);
    
            return ownerRepository.save(owner);
        } catch (Exception e) {
            LOGGER.error("Error while uploading owner image", e);
            throw new RuntimeException("Error while uploading owner image: " + e.getMessage(), e);
        }
    }
    


    // ----------------------------------     update Owner Password    -----------------------------------

    @Override
    public Owner updateOwnerPassword(UpdatePasswordForm form, Long id) {
        try {
            Owner owner = ownerRepository.findById(id).get();
            Boolean verifyOldPassword = bCryptPasswordEncoder.matches(form.getOldPassword(), owner.getPassword()); 
            if (form.isPasswordMatching() && verifyOldPassword) {
                owner.setPassword(bCryptPasswordEncoder.encode(form.getNewPassword()));
                return ownerRepository.save(owner);
            } else {
                throw new RuntimeException("This new password doesn't matches the old password !");
            }
        } catch (Exception e) {
            LOGGER.error("Error while updating the password for the owner", e);
            throw new RuntimeException("Error while updating the password for the owner: " + e.getMessage(), e);
        }
    }


    // ---------------------------------- delete Owner -----------------------------------

    @Override
    public String deleteOwner(Long id) {
        try {
            if (!ownerRepository.existsById(id)) {
                throw new RuntimeException("Owner not found with id: " + id);
            }
            ownerRepository.deleteById(id);
            return "Owner deleted successfully";
        } catch (Exception e) {
            LOGGER.error("Error while deleting owner with id " + id, e);
            throw new RuntimeException("Error while deleting owner with id " + id + ": " + e.getMessage(), e);
        }
    }




    // ---------------------------------- get Active Owners -----------------------------------

    @Override
    public Page<Owner> getActiveOwners(Pageable pageable) {
        try {
            return ownerRepository.findByAccountState(AccountStateOption.Active, pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding active owners", e);
            throw new RuntimeException("Failed to find active owners: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- get Diactive Owners -----------------------------------

    @Override
    public Page<Owner> getDisabledOwners(Pageable pageable) {
        try {
            return ownerRepository.findByAccountState(AccountStateOption.Disabled, pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding diactive owners", e);
            throw new RuntimeException("Failed to find diactive owners: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- change Owner Account State -----------------------------------

    @Override
    public Owner changeOwnerAccountState(Long ownerId) {
        try {
            Owner owner = ownerRepository.findById(ownerId)
            .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

            AccountStateOption ownerOldState = owner.getAccountState();
            if(ownerOldState == AccountStateOption.Active){
                owner.setAccountState(AccountStateOption.Disabled);
            }else{
                owner.setAccountState(AccountStateOption.Active);

            }

            return ownerRepository.save(owner);
        } catch (Exception e) {
            LOGGER.error("Error while changing owner account state", e);
            throw new RuntimeException("Error while changing owner account state: " + e.getMessage(), e);
        }
    }









  
}
