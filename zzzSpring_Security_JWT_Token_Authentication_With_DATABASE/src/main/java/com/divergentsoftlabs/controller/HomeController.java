package com.divergentsoftlabs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("/welcome")
	public String getWelcome()
	{
		return "Welcome To JWT Token";
	}
}
