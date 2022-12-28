package com.divergentsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgRank11OAuth2AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgRank11OAuth2AuthorizationServerApplication.class, args);
	}
	
	//it is use when sometimes you are already logged in with facebook so when you click
	//on login with facebook so it will not ask again facebook password
	
	/*
	 * Grant Types=>
	 *   authorization_code
	 *   implicit *****-> deprecated
	 *   client_credentials
	 *   refresh_token
	 *   password *****->deprecated
	 * */
	
	// http://localhost:8080/oauth/token?grant_type=password&username=user&password=123&scope=read
	//you will get token with username and password
}
