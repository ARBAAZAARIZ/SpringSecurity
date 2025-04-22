package com.arbaaz.SpringSecurity8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecurity8Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity8Application.class, args);
	}

}
