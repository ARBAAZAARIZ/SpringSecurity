package com.arbaaz.SpringSecurity9.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountNumber;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
