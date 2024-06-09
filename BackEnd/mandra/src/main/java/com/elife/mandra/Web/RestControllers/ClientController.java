package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.Web.Requests.ClientForms.UpdateClientForm;
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


@PostMapping("/register")
    public ResponseEntity<?> registerClient(@Valid @RequestBody Client client, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Client registeredClient = clientService.registerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredClient);
    }


@PutMapping("/{id}/edit")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id,@Valid @RequestBody UpdateClientForm client,
    BindingResult result) {
    if (result.hasErrors()){
        StringBuilder errors = new StringBuilder();
        result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
    Client updateClient = clientService.updateClient(id, client);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateClient);
}
    


 @GetMapping("")
 public ResponseEntity<Object> getClients() {
     List<Client> Clients = clientService.getClients();
    return ResponseEntity.status(HttpStatus.OK).body(Clients);
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<Object> getClients( @PathVariable(value = "id") Long id  ) {
    Client Client= clientService.getClientById(id);
    return ResponseEntity.status(HttpStatus.OK).body(Client);
 }
 

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




