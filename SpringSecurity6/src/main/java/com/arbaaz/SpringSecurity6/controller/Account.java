package com.arbaaz.SpringSecurity6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Account {

    @GetMapping("/myAccount")
    public String getAccount() {
        return "Account details from Database";
    }
}
