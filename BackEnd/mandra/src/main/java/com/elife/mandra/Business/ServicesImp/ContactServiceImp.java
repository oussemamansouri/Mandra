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





   

}
