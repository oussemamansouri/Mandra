package com.elife.mandra.Business.Services;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.Web.Requests.ClientForms.RegisterClientForm;
import com.elife.mandra.Web.Requests.ClientForms.UpdateClientForm;

public interface ClientService {

    //Create operation
    public Client registerClient(RegisterClientForm client);

    //Read operation
    public List<Client> getClients();
    public Client getClientById(Long id);
 
    //Update operation
    public Client updateClient(Long id, UpdateClientForm client);
    public Client updateClientImage(Long id, MultipartFile image);
    public Client updateClientPassword();

    //Delete operation
    public String deleteClientById(Long id); 

}
