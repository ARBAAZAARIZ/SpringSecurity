package com.arbaaz.SpringSecurity11.controller;

import com.arbaaz.SpringSecurity11.model.LoginRequest;
import com.arbaaz.SpringSecurity11.model.NexusCustomer;
import com.arbaaz.SpringSecurity11.repository.NexusCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private NexusCustomerRepository nexusCustomerRepository;

    @GetMapping("/testUser")
    public ResponseEntity<?> testUserFetch(@RequestBody LoginRequest loginRequest){
        Optional<NexusCustomer> customer =nexusCustomerRepository.findByEmail(loginRequest.getEmail());

        if(customer.isPresent()){
            return ResponseEntity.ok(customer.get());
        }else{
            return ResponseEntity.ok("User not found");
        }
    }
}
