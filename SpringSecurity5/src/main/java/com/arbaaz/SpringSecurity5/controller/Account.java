package com.arbaaz.SpringSecurity5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Account {

    @GetMapping("/myAccount")
    public String getAccountDetails(){
        return "Here are account details from DB";
    }


}
