package com.arbaaz.SpringSecurity6.service;

import com.arbaaz.SpringSecurity6.model.UserInfo;
import com.arbaaz.SpringSecurity6.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserInfoRepository userInfoRepository;

    public ResponseEntity<?> register(UserInfo userInfo){

        
        userInfo.setPwd(passwordEncoder.encode(userInfo.getPwd()));
        UserInfo newUserInfo= userInfoRepository.save(userInfo);

        if(newUserInfo!=null){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Given user details are successfully registered");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("user registration failed");
        }
        }

    }

