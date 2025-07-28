package com.arbaaz.SpringSecurity11_1.controller;

import com.arbaaz.SpringSecurity11_1.model.Accounts;
import com.arbaaz.SpringSecurity11_1.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/accounts")
public class AccountController {

    @Autowired
    AccountServices accountServices;

    @GetMapping
    @PostAuthorize("hasAuthority('USER')")
    public ResponseEntity<Accounts> getAccount(Authentication authentication){
        return accountServices.getAccount(authentication);
    }

}
