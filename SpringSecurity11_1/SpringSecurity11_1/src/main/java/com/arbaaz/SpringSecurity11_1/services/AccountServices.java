package com.arbaaz.SpringSecurity11_1.services;

import com.arbaaz.SpringSecurity11_1.model.Accounts;
import com.arbaaz.SpringSecurity11_1.model.NexusCustomer;
import com.arbaaz.SpringSecurity11_1.repository.AccountRepository;
import com.arbaaz.SpringSecurity11_1.repository.NexusCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AccountServices {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NexusCustomerRepository nexusCustomerRepository;

    public ResponseEntity<Accounts> getAccount(Authentication authentication){

        NexusCustomer customer= nexusCustomerRepository.findByEmail(authentication.getName()).orElseThrow(
                ()-> new RuntimeException("Something went wrong, not able to find user")
        );

        Accounts account=accountRepository.findByCustomer_CustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new RuntimeException("Something went wrong, not able to find account." +
                        "Wait for some time or train again, If maid newly account then wait for some time")
        );
        

        return ResponseEntity.ok(account);

    }

}
