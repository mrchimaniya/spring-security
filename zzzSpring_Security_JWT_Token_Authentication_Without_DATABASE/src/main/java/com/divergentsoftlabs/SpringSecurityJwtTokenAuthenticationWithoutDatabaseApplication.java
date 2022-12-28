package com.divergentsoftlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityJwtTokenAuthenticationWithoutDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtTokenAuthenticationWithoutDatabaseApplication.class, args);
	}

	/*
	 step1= send request for /token endpoint attach username & password in raw body
	        got token which will be bearer token
	 step2= send request for acessing /hello resource attach token as a bearer token and you will be able to get response	 
	 */
}
