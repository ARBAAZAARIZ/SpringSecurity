package com.arbaaz.SpringSecurity9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AdminPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String projectName;
    private String projectDescription;

}
