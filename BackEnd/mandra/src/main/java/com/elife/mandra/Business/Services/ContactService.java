package com.elife.mandra.Business.Services;

import com.elife.mandra.DAO.Entities.Contact;
import com.elife.mandra.Web.Requests.ContactForms.ContactForm;

public interface ContactService {

    //Create Operation
    public Contact addContact(ContactForm contactForm);

}
