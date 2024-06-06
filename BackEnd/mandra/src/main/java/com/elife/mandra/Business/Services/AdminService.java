package com.elife.mandra.Business.Services;

import com.elife.mandra.DAO.Entities.Admin;

public interface AdminService {

    //Create operation
    public Admin addAdmin(Admin admin);

    //Read operation
    public Admin getAdmin();
 
    //update operation
    public Admin updateAdmin( Admin admin);
    public Admin updateAdminImage();


}
