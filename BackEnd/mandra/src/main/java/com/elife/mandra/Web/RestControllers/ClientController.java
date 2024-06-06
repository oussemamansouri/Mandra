package com.elife.mandra.Web.RestControllers;

import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.ClientService;
import com.elife.mandra.DAO.Entities.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ClientController {

   
 final ClientService clientService;
 public ClientController(ClientService clientService){
    this.clientService = clientService;
 }   


 @PostMapping("/clients/create")
 public ResponseEntity<Object> createClient(@RequestBody Client client) {
     return new ResponseEntity<>(this.clientService.addClient(client),HttpStatus.OK);
 }


 @GetMapping("/clients")
 public ResponseEntity<Object> getClients() {
     return new ResponseEntity<>(this.clientService.getClients(),HttpStatus.OK);
 }
 
 



}
