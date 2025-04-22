package com.arbaaz.SpringSecurity8.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardId;

    @Column(nullable = false)
    @Size(max = 100)
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private NexusCustomer customer;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private String totalLimit;

    @Column(nullable = false)
    private int amountUsed;

    @Column(nullable = false)
    private int availableAmount;

    @CreatedDate
    private Instant createdDate;


}
