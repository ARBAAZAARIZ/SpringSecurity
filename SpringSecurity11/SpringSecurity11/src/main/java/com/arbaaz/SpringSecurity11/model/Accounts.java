package com.arbaaz.SpringSecurity11.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.Instant;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountNumber;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private NexusCustomer customer;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private String branchAddress;


    @OneToMany(mappedBy = "accountNumber")
    @JsonIgnore
    private List<AccountTransactions> accountTransactions;

    @CreatedDate
    private Instant createdDate;



}
