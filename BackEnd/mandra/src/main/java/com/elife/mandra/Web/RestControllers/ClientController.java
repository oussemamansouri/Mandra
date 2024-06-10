package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.Web.Requests.UserForms.AddUserForm;
import com.elife.mandra.Web.Requests.UserForms.UpdatePasswordForm;
import com.elife.mandra.Web.Requests.UserForms.UpdateUserForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/clients")
public class ClientController {

   
 final ClientService clientService;
 public ClientController(ClientService clientService){
    this.clientService = clientService;
 }   

// ----------------------------------      register Client endpoint     -----------------------------------

@PostMapping("/register")
    public ResponseEntity<?> registerClient(@Valid @RequestBody AddUserForm client, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Client registeredClient = clientService.registerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredClient);
    }




// ----------------------------------      update Client endpoint     -----------------------------------

@PutMapping("/{id}/edit")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdateUserForm client,
    BindingResult result) {
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Client updateClient = clientService.updateClient(id, client);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateClient);
}



// ----------------------------------      update Client Password endpoint     -----------------------------------

@PutMapping("/{id}/edit-password")
    public ResponseEntity<Object> updateClientPasswoed(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdatePasswordForm form,
    BindingResult result) {
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Client updateClientPassword = clientService.updateClientPassword(form, id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateClientPassword);
}




// ----------------------------------      edit image endpoint     -----------------------------------

   @PutMapping("/{id}/edit-image")
    public ResponseEntity<Object> updateClientImage(@PathVariable(value = "id") Long id, 
                                                    @RequestParam("image") MultipartFile image) {
        try {
            Client updatedClient = clientService.updateClientImage(id, image);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedClient);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error while updating client image", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    



// ----------------------------------      get Clients endpoint     -----------------------------------

 @GetMapping("")
 public ResponseEntity<Object> getClients() {
     List<Client> Clients = clientService.getClients();
    return ResponseEntity.status(HttpStatus.OK).body(Clients);
 }



 // ----------------------------------      get Client By Id endpoint     -----------------------------------

 @GetMapping("/{id}")
 public ResponseEntity<Object> getClients( @PathVariable(value = "id") Long id  ) {
    Client Client= clientService.getClientById(id);
    return ResponseEntity.status(HttpStatus.OK).body(Client);
 }
 



// ----------------------------------      delete Client endpoint     -----------------------------------

 @DeleteMapping("/{id}/delete")
 public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
     try {
         String resMessage = clientService.deleteClientById(id);
         return ResponseEntity.status(HttpStatus.OK).body(resMessage);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while deleting client", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }
 


 }




