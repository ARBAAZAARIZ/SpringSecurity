package com.arbaaz.SpringSecurity9.config;

import com.arbaaz.SpringSecurity9.filter.CsrfCookieFilter;
import com.arbaaz.SpringSecurity9.filter.RequestValidationAfterFilter;
import com.arbaaz.SpringSecurity9.filter.RequestValidationBeforeFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

@Configuration
public class ProjectSecurityConfig {

    @Autowired
    @Lazy
    ProjectAuthenticationProvider projectAuthenticationProvider;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)  throws Exception{

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler=new CsrfTokenRequestAttributeHandler();

        http.securityContext(contextConfig->contextConfig.requireExplicitSave(false))
                .sessionManagement(sessionConfig-> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.authorizeHttpRequests(requests->requests
                .requestMatchers("/contact").permitAll());


        http.cors(corsConfig->corsConfig.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config=new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        }));

        http.authenticationProvider(projectAuthenticationProvider);

        http.csrf(csrfConfig->csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact","/register","/logout")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterBefore(new RequestValidationBeforeFilter(),BasicAuthenticationFilter.class)
                .addFilterAfter(new RequestValidationAfterFilter(),BasicAuthenticationFilter.class)
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);


//        http.authorizeHttpRequests(requests->requests
//                .requestMatchers("/account").hasAnyAuthority("ViewAccount")
//                .requestMatchers("/Contact","/Notices").permitAll()
//                .requestMatchers("/adminPanel").hasAuthority("ViewAdmin")
//                .requestMatchers("/customer").authenticated()
//        );

        http.authorizeHttpRequests(requests->requests
                .requestMatchers("/account").hasAnyRole("USER","ADMIN")
                .requestMatchers("/contact","/Notices").permitAll()
                .requestMatchers("/adminPanel").hasRole("ADMIN")
                .requestMatchers("/customer").authenticated()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
