package com.arbaaz.SpringSecurity11_1.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
