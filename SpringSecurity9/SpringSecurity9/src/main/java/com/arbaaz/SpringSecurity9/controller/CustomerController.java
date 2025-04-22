package com.arbaaz.SpringSecurity9.controller;

import com.arbaaz.SpringSecurity9.model.Customer;
import com.arbaaz.SpringSecurity9.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customer")
    public String getCustomer(Authentication authentication) {

        Optional<Customer> customer=customerRepository.findByUsername(authentication.getName());
        if(customer.isPresent()){
            return customer.get().getUsername();
        }else{
            return "no user found";
        }
    }
}
