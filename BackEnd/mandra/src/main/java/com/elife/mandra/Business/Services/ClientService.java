package com.elife.mandra.Business.Services;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.Web.Requests.ClientForms.UpdateClientForm;
import com.elife.mandra.Web.Requests.UserForms.AddUserForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;

public interface ClientService {

    //Create operation
    public Client registerClient(AddUserForm client);

    //Read operation
    public List<Client> getClients();
    public Client getClientById(Long id);
 
    //Update operation
    public Client updateClient(Long id, UpdateClientForm client);
    public Client updateClientImage(Long id, MultipartFile image);
    public Client updateClientPassword(UpdatePasswordForm form, Long id);

    //Delete operation
    public String deleteClientById(Long id); 

}
