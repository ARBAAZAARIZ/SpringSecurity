package com.arbaaz.SpringSecurity8.controller;

import com.arbaaz.SpringSecurity8.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/myBalance")
    public ResponseEntity<?> getBalanceDetails( @RequestParam long id){
        return balanceService.getBalanceDetails(id);
    }


}
