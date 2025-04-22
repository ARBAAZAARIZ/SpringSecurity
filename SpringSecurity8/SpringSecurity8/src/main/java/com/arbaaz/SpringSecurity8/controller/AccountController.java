package com.arbaaz.SpringSecurity8.controller;

import com.arbaaz.SpringSecurity8.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails( @RequestParam long id){
        return accountService.getAccountDetails(id);
    }



}
