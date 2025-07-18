package com.arbaaz.SpringSecurity11_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString(exclude = "customer")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name ;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    NexusCustomer customer;


}
