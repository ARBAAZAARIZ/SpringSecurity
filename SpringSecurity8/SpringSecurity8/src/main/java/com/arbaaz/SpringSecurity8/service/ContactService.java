package com.arbaaz.SpringSecurity8.service;

import com.arbaaz.SpringSecurity8.model.Contact;
import com.arbaaz.SpringSecurity8.repository.ContactRepository;
import com.arbaaz.SpringSecurity8.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ResponseWrapper responseWrapper;


    public ResponseEntity<?> saveContactInquiryDetails(Contact contact){
        System.out.println("Received Contact: " + contact); // Debug input

        Contact contactInfoSaved = contactRepository.save(contact);

        if (contactInfoSaved == null) {
            System.out.println("ERROR: Contact could not be saved!");
            return new ResponseEntity<>("Failed to save contact", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("Saved Contact: " + contactInfoSaved); // Debug saved data
        responseWrapper.setMessage("Contact Inquiry saved");
        responseWrapper.setData(contactInfoSaved);

        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

}
