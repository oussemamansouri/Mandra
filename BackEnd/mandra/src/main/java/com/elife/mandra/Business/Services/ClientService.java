package com.elife.mandra.Business.Services;


import java.util.List;

import com.elife.mandra.DAO.Entities.Client;

public interface ClientService {

    //Create operation
    public Client addClient(Client client);
    public Client registerClient(Client client);

    //Read operation
    public List<Client> getClients();
    public Client getClientById(Long id);
 
    //Update operation
    public Client updateClient(Client client);
    public Client updateClientImage();
    public Client updateClientPassword();

    //Delete operation
    public String deleteClient(Long id); 

}
