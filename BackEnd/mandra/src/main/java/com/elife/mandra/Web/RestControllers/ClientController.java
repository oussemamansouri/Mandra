package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
 public Client registerClient(@RequestBody Client client) {
     return clientService.registerClient(client);
 }


 @GetMapping("")
 public ResponseEntity<Object> getClients() {
     return new ResponseEntity<>(this.clientService.getClients(),HttpStatus.OK);
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<Object> getClients( @PathVariable(value = "id") Long id  ) {
     return new ResponseEntity<>(this.clientService.getClientById(id),HttpStatus.OK);
 }
 

 @DeleteMapping("/{id}/delete")
 public ResponseEntity<Object> putMethodName(@PathVariable Long id) {
    return new ResponseEntity<>(this.clientService.deleteClient(id),HttpStatus.OK);
}


 }




