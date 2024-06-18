package com.elife.mandra.Web.RestControllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.ContactService;
import com.elife.mandra.DAO.Entities.Contact;
import com.elife.mandra.Web.Requests.ContactForms.ContactForm;
import com.elife.mandra.Web.Responses.ErrorResponse;

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





    // ---------------------------------- get Contacts endpoint -----------------------------------

 @GetMapping("")
    public ResponseEntity<Object> getContacts(@PageableDefault(size = 10) Pageable pageable) {
        try{
       Page<Contact> contactPage = contactService.getContacts(pageable);
       return ResponseEntity.status(HttpStatus.OK).body(contactPage);
        }catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Error while getting contacts ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
