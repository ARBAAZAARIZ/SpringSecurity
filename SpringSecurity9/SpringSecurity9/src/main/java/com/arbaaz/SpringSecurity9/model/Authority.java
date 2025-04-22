package com.arbaaz.SpringSecurity9.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
