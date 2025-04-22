package com.arbaaz.SpringSecurity8.controller;

import com.arbaaz.SpringSecurity8.service.NoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {


    @Autowired
    NoticesService noticesService;

    @GetMapping("/notices")
    public ResponseEntity<?> getNoticesDetails(){
        return noticesService.getNoticesDetails();
    }
}
