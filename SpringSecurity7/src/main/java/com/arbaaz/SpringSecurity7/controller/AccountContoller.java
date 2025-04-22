package com.arbaaz.SpringSecurity7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountContoller {

    @GetMapping("/myAccount")
    public String accountDetails(){
        return "Account Details";
    }
}
