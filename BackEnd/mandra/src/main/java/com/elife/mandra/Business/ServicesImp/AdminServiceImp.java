package com.elife.mandra.Business.ServicesImp;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.AdminService;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.AdminRepository;
import com.elife.mandra.DAO.Repositories.ClientRepository;
import com.elife.mandra.DAO.Repositories.OwnerRepository;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

@Service
public class AdminServiceImp implements AdminService{

      private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImp.class);

    final ClientRepository clientRepository ;
    final OwnerRepository ownerRepository;
    final AdminRepository adminRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final FileService fileService;
    
    public AdminServiceImp(ClientRepository clientRepository,
     OwnerRepository ownerRepository, AdminRepository adminRepository,
     BCryptPasswordEncoder bCryptPasswordEncoder, FileService fileService){

        this.clientRepository = clientRepository;
        this.ownerRepository = ownerRepository;
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.fileService = fileService;
    }

    //or you can use the Field injection
    // @Autowired
    // ClientRepository clientRepository ;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    // private FileService fileService ;



 // ---------------------------------- Create Admin -----------------------------------

public void createDefaultAdmin() {
    
    // Check if the email is already used by any user type
    boolean emailExists =  adminRepository.existsByEmail("admin@gmail.com");

    if (!emailExists) {
        try {
               Admin admin = new Admin("Oussama",
                "Mansouri",
                "admin@gmail.com" ,
                bCryptPasswordEncoder.encode("12345AZERT@") ,
                "98650478" ,
                RoleOption.Admin,
                 null );
                 adminRepository.save(admin);
        } catch (Exception e) {
            LOGGER.error("Error while adding admin", e);
            throw new RuntimeException("Error while adding admin: " + e.getMessage(), e);
        }
    }
    }




     // ----------------------------------     get Admin By Id      -----------------------------------

    @Override
    public Admin getAdminById(Long id) {
        try {
            return adminRepository.findById(id).get();
        } catch (Exception e) {
            LOGGER.error("Error while finding admin with this id :" +id+" :", e);
            throw new RuntimeException("Failed to find admin with this id "+id+" : " + e.getMessage(), e);
        }
    }




     // ----------------------------------     update Admin      -----------------------------------

    @Override
    public Admin updateAdmin(Long id, UpdateUserForm admin) {
         try {
            Admin adm = adminRepository.getReferenceById(id);
            adm.setFirstname(admin.getFirstname());
            adm.setLastname(admin.getLastname());
            adm.setPhoneNumber(admin.getPhoneNumber());
            return adminRepository.save(adm);
        } catch (Exception e) {
            LOGGER.error("Error while updating admin", e);
            throw new RuntimeException("Error while updating admin: " + e.getMessage(), e);
        }
    }



    // ----------------------------------      update admin Image     -----------------------------------

    @Override
    public Admin updateAdminImage(Long id, MultipartFile image) {
       try {

            // Fetch the admin first
            Admin admin = adminRepository.findById(id).orElse(null);
            if (admin == null) {
                throw new RuntimeException("Failed to find admin with this id: " + id);
            }
            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image file type");
            }
    
            String imagePath = fileService.saveFile(image); // Save the image and get the path
            admin.setImage(imagePath);
            return adminRepository.save(admin);
        } catch (Exception e) {
            LOGGER.error("Error while updating admin image", e);
            throw new RuntimeException("Error while updating admin image: " + e.getMessage(), e);
        }
    }




    // ----------------------------------      update admin password Image     -----------------------------------

    @Override
    public Admin updateAdminPassword(UpdatePasswordForm form, Long id) {
       try {
            Admin admin = adminRepository.findById(id).get();
            Boolean verifyOldPassword = bCryptPasswordEncoder.matches(form.getOldPassword(), admin.getPassword()); 
            if (form.isPasswordMatching() && verifyOldPassword) {
                admin.setPassword(bCryptPasswordEncoder.encode(form.getNewPassword()));
                return adminRepository.save(admin);
            } else {
                throw new RuntimeException("This new password doesn't matches the old password !");
            }
        } catch (Exception e) {
            LOGGER.error("Error while updating the password for the admin", e);
            throw new RuntimeException("Error while updating the password for the admin: " + e.getMessage(), e);
        }

    }

}
