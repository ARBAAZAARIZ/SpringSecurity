package com.arbaaz.SpringSecurity11.responsewrapper;

import com.arbaaz.SpringSecurity11.model.Accounts;
import com.arbaaz.SpringSecurity11.model.Card;
import com.arbaaz.SpringSecurity11.model.Loans;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {

    private String email;
    private Accounts accounts;
    private List<Card> card;
    private List<Loans> loans;

    private String message;



}
