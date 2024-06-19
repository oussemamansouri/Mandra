package com.elife.mandra.Business.Services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.Web.Requests.UserForms.AddUserForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

public interface ClientService {

    //Create operation
    public Client registerClient(AddUserForm client);

    //Read operation
    public Page<Client> getClients(Pageable pageable);
    public Client getClientById(Long id);
    public Page<Client> getActiveClients(Pageable pageable);
    public Page<Client> getDisabledClients(Pageable pageable);

 
    //Update operation
    public Client updateClient(Long id, UpdateUserForm client);
    public Client updateClientImage(Long id, MultipartFile image);
    public Client updateClientPassword(UpdatePasswordForm form, Long id);

    //Delete operation
    public String deleteClientById(Long id); 

}
