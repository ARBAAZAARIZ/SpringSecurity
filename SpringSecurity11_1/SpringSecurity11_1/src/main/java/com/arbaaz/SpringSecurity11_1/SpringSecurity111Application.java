package com.arbaaz.SpringSecurity11_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringSecurity111Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity111Application.class, args);
	}

}
