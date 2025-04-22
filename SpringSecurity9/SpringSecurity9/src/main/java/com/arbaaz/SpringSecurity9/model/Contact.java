package com.arbaaz.SpringSecurity9.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
}
