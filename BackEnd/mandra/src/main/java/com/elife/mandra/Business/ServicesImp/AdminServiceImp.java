package com.elife.mandra.Business.ServicesImp;

import java.util.List;

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



 // ----------------------------------     addAdmin      -----------------------------------

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




     // ----------------------------------     getAdminById      -----------------------------------

    @Override
    public Admin getAdminById(Long id) {
        try {
            return adminRepository.findById(id).get();
        } catch (Exception e) {
            LOGGER.error("Error while finding admin with this id :" +id+" :", e);
            throw new RuntimeException("Failed to find admin with this id "+id+" : " + e.getMessage(), e);
        }
    }



    

    @Override
    public Admin updateAdmin(Long id, UpdateUserForm client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAdmin'");
    }

    @Override
    public Admin updateAdminImage(Long id, MultipartFile image) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAdminImage'");
    }

    @Override
    public Admin updateAdminPassword(UpdatePasswordForm form, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAdminPassword'");
    }

}
