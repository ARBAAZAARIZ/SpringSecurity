package com.arbaaz.SpringSecurity9.controller;

import com.arbaaz.SpringSecurity9.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminPanelController {

    @Autowired
    ResponseWrapper responseWrapper;

    @GetMapping("/adminPanel")
    public String getAdminPanel(){


        return " admin panel";
    }
}
