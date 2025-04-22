package com.arbaaz.SpringSecurity4.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String email;
    private String pwd;
    private String role;


}
