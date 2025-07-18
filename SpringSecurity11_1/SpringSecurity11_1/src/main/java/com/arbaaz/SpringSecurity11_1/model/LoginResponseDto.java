package com.arbaaz.SpringSecurity11_1.model;

import java.util.List;

public record LoginResponseDto(String username, List<String> roles) {}
