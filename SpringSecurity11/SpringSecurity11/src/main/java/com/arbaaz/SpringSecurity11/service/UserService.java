package com.arbaaz.SpringSecurity11.service;

import com.arbaaz.SpringSecurity11.model.NexusCustomer;
import com.arbaaz.SpringSecurity11.repository.AccountRepository;
import com.arbaaz.SpringSecurity11.repository.CardRepository;
import com.arbaaz.SpringSecurity11.repository.LoansRepository;
import com.arbaaz.SpringSecurity11.repository.NexusCustomerRepository;
import com.arbaaz.SpringSecurity11.responsewrapper.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private NexusCustomerRepository nexusCustomerRepository;


    public ResponseEntity<?> getUserDetails(Authentication authentication){

        NexusCustomer nexusCustomer = nexusCustomerRepository.findByEmail(authentication.getName()).orElseThrow(
                ()->new RuntimeException("User Not Found Exception")
        );

         ProfileResponse profileResponse=new ProfileResponse();

        profileResponse.setEmail(nexusCustomer.getEmail());
        profileResponse.setAccounts(accountRepository.findByCustomerId(nexusCustomer.getCustomerId()).orElse(null));
        profileResponse.setCard(cardRepository.findByCustomer(nexusCustomer.getCustomerId()));
        profileResponse.setLoans(loansRepository.findByCustomerIdOrderByStartDtDesc(nexusCustomer.getCustomerId()));
        profileResponse.setMessage(" Data Fetched Successfully, If some fields are null means your haven't used that field ");

        return new ResponseEntity<>(profileResponse, HttpStatus.FOUND);


    }



}
