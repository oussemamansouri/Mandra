package com.elife.mandra.Business.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elife.mandra.DAO.Entities.Contact;
import com.elife.mandra.Web.Requests.ContactForms.ContactForm;

public interface ContactService {

    //Create Operation
    public Contact addContact(ContactForm contactForm);

    //Read Operation
    public Page<Contact> getContacts(Pageable pageable);

}
