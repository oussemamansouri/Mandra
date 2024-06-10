package com.elife.mandra.Business.Services;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

public interface AdminService {

    //Create operation
    public Admin addAdmin(Admin admin);

    //Read operation
    public Admin getAdminById(Long id);
 
    //Update operation
    public Admin updateAdmin(Long id, UpdateUserForm client);
    public Admin updateAdminImage(Long id, MultipartFile image);
    public Admin updateAdminPassword(UpdatePasswordForm form, Long id);

    //Delete operation
    // public String deleteAdminById(Long id); 

}
