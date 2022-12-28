package com.divergentsoftlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.divergentsl")
public class SpringSecurityJwtTokenAuthenticationWithoutDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtTokenAuthenticationWithoutDatabaseApplication.class, args);
	}

}
