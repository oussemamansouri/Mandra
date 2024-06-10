package com.elife.mandra.Business.ServicesImp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.AdminService;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.AdminRepository;
import com.elife.mandra.Web.Requests.UserForms.AddUserForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

@Service
public class AdminServiceImp implements AdminService{

      private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImp.class);

      final AdminRepository adminRepository;
       public AdminServiceImp(AdminRepository adminRepository){
          this.adminRepository = adminRepository;
    }

    //or you can use the Field injection
    // @Autowired
    // ClientRepository clientRepository ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



 // ----------------------------------     add Admin      -----------------------------------

public Admin addAdmin(AddUserForm adminForm) {
        try {
            List<Admin> nbAdmins = adminRepository.findByEmail(adminForm.getEmail());
            if (nbAdmins.isEmpty()) {
                adminForm.setPassword(bCryptPasswordEncoder.encode(adminForm.getPassword()));
                Admin newAdmin = new Admin(
                    adminForm.getFirstname(),
                    adminForm.getLastname(),
                    adminForm.getEmail(),
                    adminForm.getPassword(),
                    adminForm.getPhoneNumber(),
                    RoleOption.Admin,
                    null,
                    AccountStateOption.Active
                );
                return adminRepository.save(newAdmin);
            } else {
                throw new RuntimeException("This email is already in use!");
            }
        } catch (Exception e) {
            LOGGER.error("Error while adding admin", e);
            throw new RuntimeException("Error while adding admin: " + e.getMessage(), e);
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
            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image file type");
            }
    
            Admin admin = adminRepository.getReferenceById(id);
            String imagePath = saveImage(image); // Save the image and get the path
            admin.setImage(imagePath);
            return adminRepository.save(admin);
        } catch (Exception e) {
            LOGGER.error("Error while updating admin image", e);
            throw new RuntimeException("Error while updating admin image: " + e.getMessage(), e);
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
