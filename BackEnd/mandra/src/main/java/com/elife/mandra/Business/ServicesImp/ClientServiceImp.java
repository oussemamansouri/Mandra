package com.elife.mandra.Business.ServicesImp;

import java.util.List;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.FileService;
import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.ClientRepository;
import com.elife.mandra.Web.Requests.UserForms.AddUserForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;

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

    @Autowired
    private FileService fileService;



// ----------------------------------      register Client     -----------------------------------

public Client registerClient(AddUserForm clientForm) {
        try {
            List<Client> nbClients = clientRepository.findByEmail(clientForm.getEmail());
            if (nbClients.isEmpty()) {
                clientForm.setPassword(bCryptPasswordEncoder.encode(clientForm.getPassword()));
                Client newClient = new Client(
                    clientForm.getFirstname(),
                    clientForm.getLastname(),
                    clientForm.getEmail(),
                    clientForm.getPassword(),
                    clientForm.getPhoneNumber(),
                    RoleOption.Client,
                    null, // image
                    AccountStateOption.Active
                );
                return clientRepository.save(newClient);
            } else {
                throw new RuntimeException("This email is already in use!");
            }
        } catch (Exception e) {
            LOGGER.error("Error while registering client", e);
            throw new RuntimeException("Error while registering client: " + e.getMessage(), e);
        }
    }
    


// ----------------------------------      update Client     -----------------------------------

@Override
    public Client updateClient(Long id, UpdateUserForm client) {
        try {
            Client cli = clientRepository.getReferenceById(id);
            cli.setFirstname(client.getFirstname());
            cli.setLastname(client.getLastname());
            cli.setPhoneNumber(client.getPhoneNumber());
            return clientRepository.save(cli);
        } catch (Exception e) {
            LOGGER.error("Error while updating client", e);
            throw new RuntimeException("Error while updating client: " + e.getMessage(), e);
        }
    }




// ----------------------------------      get Clients     -----------------------------------

    @Override
    public Page<Client> getClients(Pageable pageable) {
        try {
            return clientRepository.findAll(pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding clients", e);
            throw new RuntimeException("Failed to find clients: " + e.getMessage(), e);
        }
    }




// ----------------------------------      get Client By Id     -----------------------------------

    @Override
    public Client getClientById(Long id) {
        try {
            return clientRepository.findById(id).get();
        } catch (Exception e) {
            LOGGER.error("Error while finding client with this id :" +id+" :", e);
            throw new RuntimeException("Failed to find client with this id "+id+" : " + e.getMessage(), e);
        }
    }






// ----------------------------------      update Client Image     -----------------------------------

    @Override
    public Client updateClientImage(Long id, MultipartFile image) {
        try {

            // Fetch the client first
            Client client = clientRepository.findById(id).orElse(null);
            if (client == null) {
                throw new RuntimeException("Failed to find client with this id: " + id);
            }

            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image file type");
            }
    
            String imagePath = fileService.saveFile(image);// Save the image and get the path
            client.setImage(imagePath);
            return clientRepository.save(client);
        } catch (Exception e) {
            LOGGER.error("Error while updating client image", e);
            throw new RuntimeException("Error while updating client image: " + e.getMessage(), e);
        }
    }





// ----------------------------------      update Client Password     -----------------------------------

    @Override
    public Client updateClientPassword(UpdatePasswordForm form, Long id) {
        try {
            Client client = clientRepository.findById(id).get();
            Boolean verifyOldPassword = bCryptPasswordEncoder.matches(form.getOldPassword(), client.getPassword()); 
            if (form.isPasswordMatching() && verifyOldPassword) {
                client.setPassword(bCryptPasswordEncoder.encode(form.getNewPassword()));
                return clientRepository.save(client);
            } else {
                throw new RuntimeException("This new password doesn't matches the old password !");
            }
        } catch (Exception e) {
            LOGGER.error("Error while updating the password for the client", e);
            throw new RuntimeException("Error while updating the password for the client: " + e.getMessage(), e);
        }
    }



// ----------------------------------      delete Client By Id     -----------------------------------

    @Override
    public String deleteClientById(Long id) {
        try {
            if (!clientRepository.existsById(id)) {
                throw new RuntimeException("Client not found with id: " + id);
            }
            clientRepository.deleteById(id);
            return "Client deleted successfully";
        } catch (Exception e) {
            LOGGER.error("Error while deleting client with id " + id, e);
            throw new RuntimeException("Error while deleting client with id " + id + ": " + e.getMessage(), e);
        }
    }




    // ---------------------------------- get Active Clients -----------------------------------

    @Override
    public Page<Client> getActiveClients(Pageable pageable) {
        try {
            return clientRepository.findByAccountState(AccountStateOption.Active, pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding active clients", e);
            throw new RuntimeException("Failed to find active clients: " + e.getMessage(), e);
        }
    }



    // ---------------------------------- get Disabled Clients -----------------------------------

    @Override
    public Page<Client> getDisabledClients(Pageable pageable) {
        try {
            return clientRepository.findByAccountState(AccountStateOption.Disabled, pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding diactive clients", e);
            throw new RuntimeException("Failed to find diactive clients: " + e.getMessage(), e);
        }
    }



        // ---------------------------------- change Client Account State -----------------------------------

    @Override
    public Client changeClientAccountState(Long clientId) {
        try {
            Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

            AccountStateOption clientOldState = client.getAccountState();
            if(clientOldState == AccountStateOption.Active){
                client.setAccountState(AccountStateOption.Disabled);
            }else{
                client.setAccountState(AccountStateOption.Active);

            }

            return clientRepository.save(client);
        } catch (Exception e) {
            LOGGER.error("Error while changing client account state", e);
            throw new RuntimeException("Error while hanging client account state: " + e.getMessage(), e);
        }
    }
    

 

}
