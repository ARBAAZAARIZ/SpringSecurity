package com.arbaaz.SpringSecurity7.config;

import com.arbaaz.SpringSecurity7.exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.requiresChannel(rcc->rcc.anyRequest().requiresInsecure()); // Only HTTPS is allowed

        http.sessionManagement(sessionConfig->sessionConfig.maximumSessions(1)
                .maxSessionsPreventsLogin(true));

        http.authorizeHttpRequests((requests)->
                requests.requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated());

        http.authorizeHttpRequests((requests)->
                requests.requestMatchers("/notices").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
