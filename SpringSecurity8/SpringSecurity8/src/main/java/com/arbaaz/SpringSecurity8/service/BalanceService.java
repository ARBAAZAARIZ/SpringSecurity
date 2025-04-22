package com.arbaaz.SpringSecurity8.service;


import com.arbaaz.SpringSecurity8.model.AccountTransactions;
import com.arbaaz.SpringSecurity8.repository.AccountTransactionRepository;
import com.arbaaz.SpringSecurity8.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private ResponseWrapper responseWrapper;

    public ResponseEntity<?> getBalanceDetails( @RequestParam long id){
        List<AccountTransactions> accountTransactions=accountTransactionRepository.findByCustomerOrderByTransactionDtDesc(id);

        if(accountTransactions.isEmpty()){
            responseWrapper.setMessage("Transaction found in your account");
            responseWrapper.setData(accountTransactions);
            return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);

        }else{
            responseWrapper.setMessage("No Transaction found in your account");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        }
    }

}
