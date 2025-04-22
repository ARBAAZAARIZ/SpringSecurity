package com.arbaaz.SpringSecurity8.service;

import com.arbaaz.SpringSecurity8.model.Card;
import com.arbaaz.SpringSecurity8.repository.CardRepository;
import com.arbaaz.SpringSecurity8.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {


    @Autowired
    CardRepository cardsRepository;

    @Autowired
    ResponseWrapper responseWrapper;

    public ResponseEntity<?> getCardDetail(long id){
        List<Card> card=cardsRepository.findByCustomer(id);


        if(card.isEmpty()){
            responseWrapper.setMessage("Card found");
            responseWrapper.setData(card);
            return new ResponseEntity<>(responseWrapper,HttpStatus.FOUND);
        }
      else{
            responseWrapper.setMessage("Card not found");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper,HttpStatus.NOT_FOUND);
        }


    }
}
