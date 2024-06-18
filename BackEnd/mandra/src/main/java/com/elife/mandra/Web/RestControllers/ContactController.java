package com.elife.mandra.Web.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.ContactService;
import com.elife.mandra.DAO.Entities.Contact;
import com.elife.mandra.Web.Requests.ContactForms.ContactForm;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {


      final ContactService contactService ;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }




  // ---------------------------------- add Contact endpoint -----------------------------------

  @PostMapping("/add")
  public ResponseEntity<?> addContact(
          @Valid @RequestBody ContactForm contactForm,
          BindingResult result) {
      if (result.hasErrors()) {
          StringBuilder errors = new StringBuilder();
          result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
          return ResponseEntity.badRequest().body(errors.toString());
      }
      try {
          Contact addedContact = contactService.addContact(contactForm);
          
          return ResponseEntity.status(HttpStatus.CREATED).body(addedContact);
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
  }




}
