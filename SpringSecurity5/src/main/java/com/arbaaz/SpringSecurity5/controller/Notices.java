package com.arbaaz.SpringSecurity5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Notices {


    @GetMapping("/notices")
    public String getNotices(){
        return "Here are the notices details from the DB";
    }

}
