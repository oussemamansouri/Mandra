package com.elife.mandra.Business.ServicesImp;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.ClientRepository;

@Service
public class ClientServiceImp implements ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImp.class);

    final ClientRepository clientRepository ;
    public ClientServiceImp(ClientRepository clientRepository ){
        this.clientRepository = clientRepository;
    }

    //or you can use the Field injection
    // @Autowired
    // ClientRepository clientRepository ;

     @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client registerClient(Client client) {
        try {
            List<Client> nbClients = clientRepository.findByEmail(client.getEmail());
            if (nbClients.isEmpty()) {
                client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
                client.setImage(null);
                client.setRole(RoleOption.Client);
                client.setAccountState("Disactive");
                return clientRepository.save(client);
            } else {
                throw new RuntimeException("This email is already in use!");
            }
        } catch (Exception e) {
            LOGGER.error("Error while registering client", e);
            throw new RuntimeException("Error while registering client: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Client> getClients() {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            // Log the exception and rethrow it or handle it accordingly
            throw new RuntimeException("Failed to find clients: " + e.getMessage(), e);
        }
    }

    @Override
    public Client getClientById(Long id) {
        try {
            return clientRepository.findById(id).get();
        } catch (Exception e) {
            // Log the exception and rethrow it or handle it accordingly
            throw new RuntimeException("Failed to find client with this id "+id+" : " + e.getMessage(), e);
        }
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
    public String deleteClient(Long id) {
        try {
             clientRepository.deleteById(id);
             return "Client was deleted successfully";
        } catch (Exception e) {
            // Log the exception and rethrow it or handle it accordingly
            throw new RuntimeException("Failed to find client with this id "+id+" : " + e.getMessage(), e);
        }
    }

 

}
