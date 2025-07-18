package com.arbaaz.SpringSecurity11.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class AccountTransactions {


    @Id
    private String transactionId;

    @PrePersist
    public void generateId(){
        if(this.transactionId == null){
            this.transactionId= UUID.randomUUID().toString();
        }
    }



    @ManyToOne
    @JoinColumn(name = "account_number")
    private Accounts accountNumber;



    @ManyToOne
    @JoinColumn(name = "customer_id")
    private NexusCustomer customer;


    @CreatedDate
    private Instant transactionDt;

    @Column(nullable = false)
    @Size(max = 200)
    private String transactionSummary;

    @Size(max = 100)
    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    @Temporal(TemporalType.DATE)
    private Date createDt;





}
