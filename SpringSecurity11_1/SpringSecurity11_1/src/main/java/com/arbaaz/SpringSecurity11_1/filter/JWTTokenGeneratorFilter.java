package com.arbaaz.SpringSecurity11_1.filter;

import com.arbaaz.SpringSecurity11_1.constants.ApplicationConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {


    private final Environment evn;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("JWT Generator Filter executed. Auth = " + authentication);

            if(null != authentication) {

                String secret = evn.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                        ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                System.out.println("Secret is " + secret);

                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                String jwt = Jwts.builder().issuer("Nexus Bank")
                        .subject("JWT Token")
                        .claim("username", authentication.getName())
                        .claim("authorities", authentication.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + 300000000))
                        .signWith(secretKey)
                        .compact();

                response.setHeader(ApplicationConstants.JWT_HEADER, jwt);
                response.setHeader("Access-Control-Expose-Headers", ApplicationConstants.JWT_HEADER);

            }

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/api/auth/token"); // Only run for token generation
    }

}
