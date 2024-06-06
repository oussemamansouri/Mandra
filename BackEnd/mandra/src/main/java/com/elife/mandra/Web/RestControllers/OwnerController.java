package com.elife.mandra.Web.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.elife.mandra.Business.Services.OwnerService;
import com.elife.mandra.DAO.Entities.Owner;

@RestController
public class OwnerController {

    final OwnerService ownerService;
 public OwnerController(OwnerService ownerService){
    this.ownerService = ownerService;
 }   


 @PostMapping("/owner/create")
 public ResponseEntity<Object> createClient(@RequestBody Owner owner) {
     return new ResponseEntity<>(this.ownerService.addOwner(owner),HttpStatus.OK);
 }

}
