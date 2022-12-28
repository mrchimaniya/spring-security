package com.divergentsl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	//you cann't send request for generating secret key again for same user 2nd user
	//beacuse in D/B there will we same secret key generated with same username beacause of 1st req so 
	//it will give you error if D/B contains same user with multiple secret key
	
	
	//1st req localhost:8080/login with uname=user password=123 in header
	// you will be able to generated key in db & assume it is like otp send in phone no
	
	//2nd req localhost:8080/login with uname=user secret_key=82000 copy from table
	// you will be able to find an authentication token in header
	
	//3rd req localhost:8080/hello with authentication token which is generated in 
	//previous means 2nd request attach this in header partS
	@GetMapping("/hello")
	public String hello(Authentication auth/* first way to get authentication object*/)
	{   
		Authentication auth2=SecurityContextHolder.getContext().getAuthentication();
		  /*second way to get authentication obj from security context()*/
		System.out.println(auth.getName());
		System.out.println(auth2.getName());
		return "<h1>Hello Welcome</h1><br><a href='/logout'>Logout</a>";
	}

}
