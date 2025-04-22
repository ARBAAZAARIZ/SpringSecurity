package com.arbaaz.SpringSecurity9.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Notices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noticeId; // Primary key

    @Column( nullable = false, length = 200)
    private String noticeSummary; // Summary of the notice

    @Column( nullable = false, length = 500)
    private String noticeDetails; // Detailed description of the notice
}
