package com.arbaaz.SpringSecurity8.service;

import com.arbaaz.SpringSecurity8.model.Accounts;
import com.arbaaz.SpringSecurity8.repository.AccountRepository;
import com.arbaaz.SpringSecurity8.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ResponseWrapper responseWrapper;


    public ResponseEntity<?> getAccountDetails(long id){
       Optional<Accounts> account=accountRepository.findByCustomerId(id);
        if(account.isPresent()){
            responseWrapper.setMessage("Account not found");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper,HttpStatus.NOT_FOUND);
        }
        responseWrapper.setMessage("Account found");
        responseWrapper.setData(account);
        return new ResponseEntity<>(responseWrapper,HttpStatus.FOUND);
    }

}
