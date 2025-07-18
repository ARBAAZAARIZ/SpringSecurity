package com.arbaaz.SpringSecurity11.service;

import com.arbaaz.SpringSecurity11.model.Accounts;
import com.arbaaz.SpringSecurity11.model.NexusCustomer;
import com.arbaaz.SpringSecurity11.repository.AccountRepository;
import com.arbaaz.SpringSecurity11.repository.NexusCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NexusCustomerRepository nexusCustomerRepository;


    public ResponseEntity<?> getAccountDetails(Authentication authentication){

        Optional<NexusCustomer> nexusCustomer=nexusCustomerRepository.findByEmail(authentication.getName());
        if(nexusCustomer.isPresent()){

            Accounts accounts=accountRepository.findByCustomerId(nexusCustomer.get().getCustomerId()).orElseThrow(
                    ()->new RuntimeException("Account not found")
            );

            return new ResponseEntity<>(accounts, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

    }

}
