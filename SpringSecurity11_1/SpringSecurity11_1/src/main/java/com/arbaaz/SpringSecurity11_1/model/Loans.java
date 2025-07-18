package com.arbaaz.SpringSecurity11_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.time.Instant;
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"customer"})
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loanNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private NexusCustomer customer;  // Reference to the customer

    @Temporal(TemporalType.DATE)
    private Date startDt;

    @Column(nullable = false)
    private String loanType;

    @Column(nullable = false)
    private int totalLoan;

    @Column(nullable = false)
    private int amountPaid;

    @Column(nullable = false)
    private int outstandingAmount;

    @CreatedDate
    private Instant createDt;
}
