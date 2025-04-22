package com.arbaaz.SpringSecurity8.service;

import com.arbaaz.SpringSecurity8.model.Loans;
import com.arbaaz.SpringSecurity8.repository.LoansRepository;
import com.arbaaz.SpringSecurity8.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoansService {

    @Autowired
    LoansRepository loansRepository;

    @Autowired
    ResponseWrapper responseWrapper;

    public ResponseEntity<?> getLoansDetails(long id){
        List<Loans> loans=loansRepository.findByCustomerIdOrderByStartDtDesc(id);

        if(loans.isEmpty()){
            responseWrapper.setMessage("No Loans found in your account");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        }else{
            responseWrapper.setMessage("This are the Loans found in your account");
            responseWrapper.setData(loans);
            return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
        }
    }
}
