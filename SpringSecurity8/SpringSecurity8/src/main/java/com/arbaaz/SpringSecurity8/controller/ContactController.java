package com.arbaaz.SpringSecurity8.controller;

import com.arbaaz.SpringSecurity8.model.Contact;
import com.arbaaz.SpringSecurity8.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<?> saveContactInquiryDetails(@RequestBody Contact contact){

        return contactService.saveContactInquiryDetails(contact);
    }

}
