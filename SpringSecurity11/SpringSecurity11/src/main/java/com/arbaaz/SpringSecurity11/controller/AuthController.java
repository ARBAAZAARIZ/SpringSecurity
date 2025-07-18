package com.arbaaz.SpringSecurity11.controller;


import com.arbaaz.SpringSecurity11.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login ( @RequestBody LoginRequest loginRequest){
        // Create an authentication token
        UsernamePasswordAuthenticationToken authToken=
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());

        // Authenticate the credentials using your custom authentication provider
        Authentication authentication=authenticationManager.authenticate(authToken);

        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
       }


       @GetMapping("/dummy")
       public void dummyApi(){
           System.out.println("CSRF Token will be set in cookie");
       }


}

//The controller accepts a JSON login request.
//
//It uses the AuthenticationManager to authenticate the user, which in turn calls your CustomAuthenticationProvider.
//
//You can decide whether to generate a JWT right here or, as in your current design, let your /user endpoint trigger the JWTTokenGeneratorFilter afterward.
