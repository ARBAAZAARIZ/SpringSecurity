package com.arbaaz.SpringSecurity11_1.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Date;

public class Notices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noticeId; // Primary key

    @Column( nullable = false, length = 200)
    private String noticeSummary; // Summary of the notice

    @Column( nullable = false, length = 500)
    private String noticeDetails; // Detailed description of the notice

    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private Date noticeBegDt; // Notice begin date


    @Temporal(TemporalType.DATE)
    private Date noticeEndDt; // Notice end date (optional)


    @CreatedDate
    private Instant createDt; // Record creation date


    @LastModifiedDate
    private Instant updateDt; // Record update date (optional)
}
