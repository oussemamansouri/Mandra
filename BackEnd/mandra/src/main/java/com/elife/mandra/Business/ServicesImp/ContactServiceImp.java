package com.elife.mandra.Business.ServicesImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.ContactService;
import com.elife.mandra.DAO.Entities.Contact;
import com.elife.mandra.DAO.Repositories.ContactRepository;
import com.elife.mandra.Web.Requests.ContactForms.ContactForm;


@Service
public class ContactServiceImp implements ContactService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImp.class);

    final ContactRepository contactRepository;
    private ContactServiceImp(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }





    // ---------------------------------- add Contact By Visiter -----------------------------------

    @Override
    public Contact addContact(ContactForm contactForm) {
         try {
        
            Contact newContact = new Contact(
                contactForm.getFirstname(),
                contactForm.getLastname(),
                contactForm.getEmail(),
                contactForm.getPhoneNumber(),
                contactForm.getSubject(),
                contactForm.getDescription()
            );

            return contactRepository.save(newContact);

        } catch (Exception e) {
            LOGGER.error("Error while adding contact", e);
            throw new RuntimeException("Error while adding contact: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- get Contacts  -----------------------------------

    @Override
    public Page<Contact> getContacts(Pageable pageable) {
        try {
            return contactRepository.findAll(pageable);
        } catch (Exception e) {
            LOGGER.error("Error while finding contacts", e);
            throw new RuntimeException("Failed to find contacts: " + e.getMessage(), e);
        }
    }




    // ---------------------------------- get Contact By Id  -----------------------------------

    @Override
    public Contact getContactyById(Long contactId) {
        try{
            return contactRepository.findById(contactId).get();
          }catch(Exception e){
            LOGGER.error("Error while Getting contact with this id : "+ contactId+" :", e);
            throw new RuntimeException("Failed to find contact with this id " + contactId + " : " + e.getMessage(), e);
          }
    }




    // ---------------------------------- delete Contact By Id  -----------------------------------

    @Override
    public String deleteContact(Long contactId) {
        try{
            if (!contactRepository.existsById(contactId)) {
                throw new RuntimeException("Specialty women not found with id: " + contactId);
            }
            contactRepository.deleteById(contactId);
            return "Contact deleted successfully";
        }catch(Exception e){
            LOGGER.error("Error while deleting contact with id " + contactId, e);
            throw new RuntimeException("Error while deleting contact with id " + contactId + ": " + e.getMessage(), e);
        }
    }





   

}
