package com.arbaaz.SpringSecurity11_1.controller;

import com.arbaaz.SpringSecurity11_1.model.NexusCustomer;
import com.arbaaz.SpringSecurity11_1.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {


    @Autowired
    private UserServices userServices;

    @GetMapping("/user")
    public ResponseEntity<NexusCustomer> getUser(Authentication authentication){
       return   userServices.getUser(authentication);
    }

}
