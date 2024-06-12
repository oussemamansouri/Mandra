package com.elife.mandra.Business.ServicesImp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.OwnerForms.AddOwnerForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;

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
                    null,
                    null,
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


    @Override
    public Owner uploadCinImage(Long id, MultipartFile cinImage) {
       try {
            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(cinImage.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image type");
            }
    
            Owner owner = ownerRepository.getReferenceById(id);
            String imagePath = saveImage(cinImage); // Save the file and get the path
            owner.setImage(imagePath);
            return ownerRepository.save(owner);
        } catch (Exception e) {
            LOGGER.error("Error while uploading owner cin image", e);
            throw new RuntimeException("Error while uploading owner cin image : " + e.getMessage(), e);
        }
    }



    // Method to save the image and return the relative path
private String saveImage(MultipartFile image) throws IOException {
    String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
    File directory = new File(uploadDir);
    
    // Create the directory if it does not exist
    if (!directory.exists()) {
        directory.mkdirs();
    }

    // Generate a unique filename
    String originalFilename = image.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFilename);
    String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

    File file = new File(directory, uniqueFilename);
    image.transferTo(file);
    return "/images/" + uniqueFilename; // Return the relative path with the unique filename
}







    @Override
    public Owner uploadProofFile(Long id, MultipartFile proofFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadProofFile'");
    }


    @Override
    public Owner updateOwner(Owner owner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwner'");
    }


    @Override
    public Owner updateOwnerImage(Long id, MultipartFile image) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwnerImage'");
    }


    @Override
    public Owner updateOwnerPassword(UpdatePasswordForm form, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwnerPassword'");
    }


    @Override
    public void deleteOwner(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOwner'");
    }









  
}
