package com.divergentsl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	
	//send request with secret key in header  localhost:8080/hello   header: auth_key=secret
	@GetMapping("/hello")
	public String hello()
	{
		return "<h1>Hello Welcome</h1>";
	}

}
