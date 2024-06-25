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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        try{
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Client registeredClient = clientService.registerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredClient);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Error while register Client", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }
    }




// ----------------------------------      update Client endpoint     -----------------------------------

@PutMapping("/{id}/edit")
@PreAuthorize("hasAnyRole('Admin', 'Client') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdateUserForm client,
    BindingResult result) {
    try{
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Client updateClient = clientService.updateClient(id, client);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateClient);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating Client", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}



// ----------------------------------      update Client Password endpoint     -----------------------------------

@PutMapping("/{id}/edit-password")
@PreAuthorize("hasAnyRole('Client') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> updateClientPasswoed(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdatePasswordForm form,
    BindingResult result) {
    try{    
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Client updateClientPassword = clientService.updateClientPassword(form, id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateClientPassword);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while updating Client password", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}




// ----------------------------------      edit image endpoint     -----------------------------------

   @PutMapping("/{id}/edit-image")
   @PreAuthorize("hasAnyRole('Admin', 'Client') and hasAuthority('UPDATE_PRIVILEGE')")

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
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
 public ResponseEntity<Object> getClients(@PageableDefault(size = 10) Pageable pageable) {
    try {
     Page<Client> ClientsPage = clientService.getClients(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(ClientsPage);
    } catch (RuntimeException e) {
          ErrorResponse errorResponse = new ErrorResponse("Error while getting clients", e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
}
 }



 // ----------------------------------      get Client By Id endpoint     -----------------------------------

 @GetMapping("/{id}")
 @PreAuthorize("hasAnyRole('Admin', 'Owner') and hasAuthority('READ_PRIVILEGE')")
 public ResponseEntity<Object> getClientById( @PathVariable(value = "id") Long id  ) {
    try {
    Client Client= clientService.getClientById(id);
    return ResponseEntity.status(HttpStatus.OK).body(Client);
    } catch (RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse("Error while getting client by this id : " + id, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }
 



// ----------------------------------      delete Client endpoint     -----------------------------------

 @DeleteMapping("/{id}/delete")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('DELETE_PRIVILEGE')")
 public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
     try {
         String resMessage = clientService.deleteClientById(id);
         return ResponseEntity.status(HttpStatus.OK).body(resMessage);
     } catch (RuntimeException e) {
         ErrorResponse errorResponse = new ErrorResponse("Error while deleting client", e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
     }
 }
 



    // ---------------------------------- get Active Clients endpoint -----------------------------------

     @GetMapping("/active")
     @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
     public ResponseEntity<Object> getActiveClients(@PageableDefault(size = 10) Pageable pageable) {
        try{
        Page<Client> clientPage = clientService.getActiveClients(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(clientPage);
        }catch(RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting Active Clients", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        }
     




      // ---------------------------------- get Disabled Clients endpoint -----------------------------------

    @GetMapping("/disabled")
    @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Object> getDisabledClients(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<Client> clientPage = clientService.getDisabledClients(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(clientPage); 
        }catch(RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting Disabled Clients", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
     }



    // ---------------------------------- change Client Account State endpoint -----------------------------------

    @PutMapping("/{clientId}/change-account-state")
    @PreAuthorize("hasAnyRole('Admin') and hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseEntity<Object> changeOwnerAccountState(@PathVariable(value = "clientId") Long clientId ) {
        try{
    Client updatedClient = clientService.changeClientAccountState(clientId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedClient);
    }catch(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse("Error while changing Client Account State", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    }   


 }




