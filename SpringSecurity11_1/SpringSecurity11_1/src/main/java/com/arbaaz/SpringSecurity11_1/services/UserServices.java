package com.arbaaz.SpringSecurity11_1.services;

import com.arbaaz.SpringSecurity11_1.model.NexusCustomer;
import com.arbaaz.SpringSecurity11_1.repository.NexusCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final NexusCustomerRepository nexusCustomerRepository;

    public ResponseEntity<NexusCustomer> getUser(Authentication authentication){
       NexusCustomer customer= nexusCustomerRepository.findByEmail(authentication.getName()).orElseThrow(
                ()-> new BadCredentialsException("Something went wrong, not able to find user")
        );

        System.out.println(customer.getAccountTransactions().stream().map(transaction->
                transaction.getTransactionAmt()).collect(Collectors.toList()));


        return ResponseEntity.ok(customer);

    }




}
