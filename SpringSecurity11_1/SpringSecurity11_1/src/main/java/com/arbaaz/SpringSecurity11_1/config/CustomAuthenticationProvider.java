package com.arbaaz.SpringSecurity11_1.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

//    private final NexusCustomerRepository nexusCustomerRepository;

    private final UserDetailsService userDetailsService;


    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email=authentication.getName();
        String rawPassword=authentication.getCredentials().toString();

//        NexusCustomer customer=nexusCustomerRepository.findByEmail(email)
//                .orElseThrow(
//                        ()-> new BadCredentialsException("Invalid email or password")
//                );
//
//        if(!passwordEncoder.matches(rawPassword, customer.getPwd())){
//            throw new BadCredentialsException("Invalid email or password");
//        }
//
//        List<GrantedAuthority> authorities= customer.getAuthorities().stream().map(
//                auth -> new SimpleGrantedAuthority(auth.getName())
//        ).collect(Collectors.toList());

        UserDetails userDetails=userDetailsService.loadUserByUsername(email);
        System.out.println("User details are "+userDetails);

        if(passwordEncoder.matches(rawPassword, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(email,null,userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("Invalid email or password");
        }


    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
