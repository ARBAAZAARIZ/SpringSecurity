package com.arbaaz.SpringSecurity8.controller;

import com.arbaaz.SpringSecurity8.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public class LoansController {



    @Autowired
    LoansService loansService;

    @GetMapping("/myLoans")
    public ResponseEntity<?> getLoansDetails( @RequestParam long id){
        return loansService.getLoansDetails(id);
    }
}
