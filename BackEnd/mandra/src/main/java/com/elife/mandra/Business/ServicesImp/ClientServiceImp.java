package com.elife.mandra.Business.ServicesImp;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Repositories.ClientRepository;

@Service
public class ClientServiceImp implements ClientService {

    final ClientRepository clientRepository ;
    public ClientServiceImp(ClientRepository clientRepository ){
        this.clientRepository = clientRepository;
    }

    //or you can use the Field injection
    // @Autowired
    // ClientRepository clientRepository ;

    @Override
    public Client addClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            // Log the exception and rethrow it or handle it accordingly
            throw new RuntimeException("Failed to save client: " + e.getMessage(), e);
        }
    }

    @Override
    public Client getClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClients'");
    }

    @Override
    public Client getClientById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientById'");
    }

    @Override
    public Client updateClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClient'");
    }

    @Override
    public Client updateClientImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClientImage'");
    }

    @Override
    public Client updateClientPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClientPassword'");
    }

    @Override
    public void deleteClient(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClient'");
    }

}
