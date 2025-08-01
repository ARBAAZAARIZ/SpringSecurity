package com.arbaaz.SpringSecurity11.model;
import jakarta.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactId; // Primary key

    @Column( length = 50, nullable = false)
    private String contactName; // Name of the contact

    @Column( length = 100, nullable = false)
    private String contactEmail; // Email address of the contact

    @Column( length = 500, nullable = false)
    private String subject; // Subject of the message

    @Column( length = 2000, nullable = false)
    private String message; // The actual message content


    @CreatedDate
    private Instant createDt; // Date when the message was created


}



