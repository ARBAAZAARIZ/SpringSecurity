package com.arbaaz.SpringSecurity8.controller;

import com.arbaaz.SpringSecurity8.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CardController {

    @Autowired
    CardService cardService;


    @GetMapping("/myCard")
    public ResponseEntity<?> getCardDetail( @RequestParam long id){
        return cardService.getCardDetail(id);
    }

}
