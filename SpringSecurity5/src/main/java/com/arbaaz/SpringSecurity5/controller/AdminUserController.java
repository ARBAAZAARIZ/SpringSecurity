package com.arbaaz.SpringSecurity5.controller;

import com.arbaaz.SpringSecurity5.model.AdminUser;
import com.arbaaz.SpringSecurity5.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {


    @Autowired
    AdminUserRepository adminUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody AdminUser adminUser){


        try{

            adminUser.setPwd(passwordEncoder.encode(adminUser.getPwd()));
           AdminUser newAdminUSer= adminUserRepository.save(adminUser);

            if(newAdminUSer!=null){
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
            }else{
                throw new Exception("User registration failed");
            }


        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
