package com.arbaaz.SpringSecurity11_1.controller;

import com.arbaaz.SpringSecurity11_1.model.Loans;
import com.arbaaz.SpringSecurity11_1.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/loans")
public class LoanController {


    @Autowired
    LoanServices loanServices;


    @GetMapping()
    public ResponseEntity<List<Loans>> getAllLoans(Authentication authentication){
        return loanServices.getAllLoans(authentication);
    }

}
