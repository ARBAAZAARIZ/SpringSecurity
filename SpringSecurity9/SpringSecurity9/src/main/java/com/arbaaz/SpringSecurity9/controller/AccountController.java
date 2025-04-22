package com.arbaaz.SpringSecurity9.controller;

import com.arbaaz.SpringSecurity9.model.Account;
import com.arbaaz.SpringSecurity9.model.Contact;
import com.arbaaz.SpringSecurity9.model.Customer;
import com.arbaaz.SpringSecurity9.model.Notices;
import com.arbaaz.SpringSecurity9.repository.AccountRepository;
import com.arbaaz.SpringSecurity9.repository.ContactRepository;
import com.arbaaz.SpringSecurity9.repository.CustomerRepository;
import com.arbaaz.SpringSecurity9.repository.NoticesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;
    @GetMapping("/account")
    public ResponseEntity<?> getAccountNumber(Authentication authentication) {
        Customer customer = customerRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Account account = accountRepository.findByCustomerId(customer.getId());

        if (account == null) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @Autowired
    NoticesRepository noticesRepository;

    @GetMapping("/notices")
    public List<Notices> getNotices(){
        return noticesRepository.findAll();
    }

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/contact")
    public Contact createContact(@RequestBody Contact contact) {

        return contactRepository.save(contact);
    }



}
