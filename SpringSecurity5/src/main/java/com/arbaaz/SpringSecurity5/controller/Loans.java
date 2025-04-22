package com.arbaaz.SpringSecurity5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Loans {


    @GetMapping("/myLoans")
    public String getLoans() {
        return "Here are loan details from DB";
    }

}
