package com.arbaaz.SpringSecurity6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Notices {

    @GetMapping("/notices")
    public String getNotices() {
        return "Notices from database";
    }

}
