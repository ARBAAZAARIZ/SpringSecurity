package com.arbaaz.SpringSecurity11.config;

import com.arbaaz.SpringSecurity11.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.arbaaz.SpringSecurity11.filter.CsrfCookieFilter;
import com.arbaaz.SpringSecurity11.filter.JWTTokenGeneratorFilter;
import com.arbaaz.SpringSecurity11.filter.JWTTokenValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Collections;
import java.util.List;

@Configuration
public class ProjectSecurityConfig {



    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http.sessionManagement(sessionConfig->
                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig-> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:5173")); // Allow Vite frontend
                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow common methods
                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(List.of("Authorization", "Content-Type","X-XSRF-TOKEN")); // Allow headers
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(List.of("Authorization")); // Allow frontend to read JWT from headers

                        config.setMaxAge(3600L); // Cache the CORS config for 1 hour
                        return config;
                    }
                }));

        http.csrf(csrfConfig->csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                .ignoringRequestMatchers("/contact","register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(request->
                        request.requestMatchers("/auth/account").hasRole("USER")
                                .requestMatchers("/myAccount").hasRole("USER")
                                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/myLoans").hasRole("USER")
                                .requestMatchers("/myCards").hasRole("USER")
                                .requestMatchers("/auth/user").authenticated()
                                .requestMatchers("/notices", "/contact", "/error",
                                        "/register", "/invalidSession", "/auth/login","/debug/testUser","/auth/dummy").permitAll());

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // Define an AuthenticationManager that uses your custom provider
    @Autowired
    @Lazy
    NexusCustomAuthenticationProvider nexusCustomAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return new ProviderManager(List.of(nexusCustomAuthenticationProvider));
    }
//    What’s important here?
//    You permit the /auth/login endpoint so that requests are not blocked by security.
//    You disable the default form login since you’re using a REST-based login.
//    You configure a custom AuthenticationManager that uses your custom provider.
//    You add your JWT filters that work on other endpoints (you can adjust the order if needed).

}
