package com.arbaaz.SpringSecurity4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Balance {

    @GetMapping("/myBalance")
    public String getBalance() {
        return "Here are the balance details from the DB";
    }
}
