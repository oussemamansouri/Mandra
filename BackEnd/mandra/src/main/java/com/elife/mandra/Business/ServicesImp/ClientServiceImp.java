package com.elife.mandra.Business.ServicesImp;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.elife.mandra.DAO.Repositories.ClientRepository;
import com.elife.mandra.Web.Requests.ClientForms.RegisterClientForm;
import com.elife.mandra.Web.Requests.ClientForms.UpdateClientForm;

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



// ----------------------------------      registerClient     -----------------------------------

public Client registerClient(RegisterClientForm clientForm) {
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
    


// ----------------------------------      updateClient     -----------------------------------

@Override
    public Client updateClient(Long id, UpdateClientForm client) {
        try {
            Client cli = clientRepository.getReferenceById(id);
            cli.setFirstname(client.getFirstname());
            cli.setLastname(client.getLastname());
            cli.setPhoneNumber(client.getPhoneNumber());
            return clientRepository.save(cli);
        } catch (Exception e) {
            LOGGER.error("Error while registering client", e);
            throw new RuntimeException("Error while registering client: " + e.getMessage(), e);
        }
    }




// ----------------------------------      getClients     -----------------------------------

    @Override
    public List<Client> getClients() {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while finding clients", e);
            throw new RuntimeException("Failed to find clients: " + e.getMessage(), e);
        }
    }




// ----------------------------------      getClientById     -----------------------------------

    @Override
    public Client getClientById(Long id) {
        try {
            return clientRepository.findById(id).get();
        } catch (Exception e) {
            LOGGER.error("Error while finding client with this id :" +id+" :", e);
            throw new RuntimeException("Failed to find client with this id "+id+" : " + e.getMessage(), e);
        }
    }






// ----------------------------------      updateClientImage     -----------------------------------

    @Override
    public Client updateClientImage(Long id, MultipartFile image) {
        try {
            // Validate if the file is an image
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new RuntimeException("Invalid image file type");
            }
    
            Client client = clientRepository.getReferenceById(id);
            String imagePath = saveImage(image); // Save the image and get the path
            client.setImage(imagePath);
            return clientRepository.save(client);
        } catch (Exception e) {
            LOGGER.error("Error while updating client image", e);
            throw new RuntimeException("Error while updating client image: " + e.getMessage(), e);
        }
    }
    

// Method to save the image and return the relative path
private String saveImage(MultipartFile image) throws IOException {
    String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
    File directory = new File(uploadDir);
    
    // Create the directory if it does not exist
    if (!directory.exists()) {
        directory.mkdirs();
    }

    // Generate a unique filename
    String originalFilename = image.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFilename);
    String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

    File file = new File(directory, uniqueFilename);
    image.transferTo(file);
    return "/images/" + uniqueFilename; // Return the relative path with the unique filename
}





// ----------------------------------      updateClientPassword     -----------------------------------

    @Override
    public Client updateClientPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClientPassword'");
    }





// ----------------------------------      deleteClientById     -----------------------------------

    @Override
    public String deleteClientById(Long id) {
        try {
            clientRepository.deleteById(id);
            return "Client deleted successfully";
        } catch (Exception e) {
            LOGGER.error("Error while deleting client with id " + id, e);
            throw new RuntimeException("Error while deleting client with id " + id + ": " + e.getMessage(), e);
        }
    }
    

 

}
