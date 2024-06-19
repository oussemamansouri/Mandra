package com.elife.mandra.Web.RestControllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
      try{    
      if (result.hasErrors()) {
          StringBuilder errors = new StringBuilder();
          result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
          return ResponseEntity.badRequest().body(errors.toString());
      }

          Contact addedContact = contactService.addContact(contactForm);
          return ResponseEntity.status(HttpStatus.CREATED).body(addedContact);
      } catch (Exception e) {
          ErrorResponse errorResponse = new ErrorResponse("Error while adding contact", e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
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



        // ---------------------------------- get Contact By Id endpoint -----------------------------------

 @GetMapping("/{contactId}")
 public ResponseEntity<Object> getSpecialtyWomenById( @PathVariable(value = "contactId") Long contactId ) {
    try{
        Contact contact = contactService.getContactyById(contactId);
    return ResponseEntity.status(HttpStatus.OK).body(contact);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while getting contact with this id :"+ contactId, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
 }




  // ---------------------------------- delete Contact endpoint -----------------------------------

 @DeleteMapping("/{contactId}/delete")
 public ResponseEntity<Object> deleteSpecialtyWomen(@PathVariable(value = "contactId") Long contactId){
    try{
     String resMessege = contactService.deleteContact(contactId);
     return ResponseEntity.status(HttpStatus.ACCEPTED).body(resMessege);
    }catch(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Error while deleting contact with this id :"+ contactId, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
 }



}
