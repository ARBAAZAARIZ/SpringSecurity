package com.arbaaz.SpringSecurity6.controller;

import com.arbaaz.SpringSecurity6.model.UserInfo;
import com.arbaaz.SpringSecurity6.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody UserInfo userInfo){
        return userInfoService.register(userInfo);
    }

}
