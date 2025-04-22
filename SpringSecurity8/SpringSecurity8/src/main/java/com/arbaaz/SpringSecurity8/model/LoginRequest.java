package com.arbaaz.SpringSecurity8.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
