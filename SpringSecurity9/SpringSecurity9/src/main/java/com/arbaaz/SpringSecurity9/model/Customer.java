package com.arbaaz.SpringSecurity9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Authority> authorities;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Account account;

}
