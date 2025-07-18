package com.arbaaz.SpringSecurity11_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"accounts","loans","card","accountTransactions","authorities"})
public class NexusCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Accounts accounts;

    @Column(nullable = false)
    @Size(min = 3, max = 100)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Size(min = 10, max = 20)
    @Column(nullable = false)
    private String mobileNumber;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    private String role;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Loans> loans;  // One customer -> Many Loans

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Card> card; // one customer -> many cards (credit or debit card)

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<AccountTransactions> accountTransactions;


    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<Authority> authorities;

    @CreatedDate
    private Instant createdDate;
}
