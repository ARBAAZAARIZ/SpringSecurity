package com.arbaaz.SpringSecurity11_1.services;

import com.arbaaz.SpringSecurity11_1.model.Loans;
import com.arbaaz.SpringSecurity11_1.model.NexusCustomer;
import com.arbaaz.SpringSecurity11_1.repository.LoanRepository;
import com.arbaaz.SpringSecurity11_1.repository.NexusCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServices {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    NexusCustomerRepository nexusCustomerRepository;

    public ResponseEntity<List<Loans>> getAllLoans(Authentication authentication){

        NexusCustomer customer= nexusCustomerRepository.findByEmail(authentication.getName()).orElseThrow(
                ()-> new RuntimeException("Something went wrong, not able to find user")
        );

       List<Loans> loansList = loanRepository.findByCustomer_CustomerId(customer.getCustomerId());

       return ResponseEntity.ok(loansList);
    }
}
