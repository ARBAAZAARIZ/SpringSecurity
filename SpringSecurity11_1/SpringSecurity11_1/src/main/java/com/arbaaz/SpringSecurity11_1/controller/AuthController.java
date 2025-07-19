package com.arbaaz.SpringSecurity11_1.controller;

import com.arbaaz.SpringSecurity11_1.constants.ApplicationConstants;
import com.arbaaz.SpringSecurity11_1.model.LoginRequest;
import com.arbaaz.SpringSecurity11_1.model.LoginResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final Environment env;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){

        System.out.println("Login request is "+loginRequest);
        System.out.println(response);
        // 1. Authenticate user
        Authentication authentication=authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(),loginRequest.getPassword()
          )
        );

        System.out.println("Authentication is "+authentication+ "from login Controller");

        // Store authentication in SecurityContext (for filters to read)
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 2. Generate JWT
        //2.1 Secrect key

        if(null != authentication){
            String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                    ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
            System.out.println("Secret is " + secret);
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            //2.2 Building JWT
            String jwt = Jwts.builder().issuer("Nexus Bank")
                    .subject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", authentication.getAuthorities().stream().map(
                            GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + 900000))
                    .signWith(secretKey)
                    .compact();

            //2.3 Adding JWT to response
            response.setHeader(ApplicationConstants.JWT_HEADER, jwt);
            response.setHeader("Access-Control-Expose-Headers", ApplicationConstants.JWT_HEADER);
        }

        assert authentication != null;
        String username = authentication.getName();
        List<String> roles=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        LoginResponseDto loginResponseDto=new LoginResponseDto(username,roles);


        return ResponseEntity.ok(loginResponseDto);

    }

    // CSRF cookie will be set by CsrfCookieFilter
    @GetMapping("/csrf")
    public ResponseEntity<Void> getCsrfToken() {
        return ResponseEntity.ok().build();
    }






}
