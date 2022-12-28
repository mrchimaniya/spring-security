package com.divergentsl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/hello")
	public String hello()
	{
		return "<h1>Hello Welcome</h1><br><a href='/logout'>Logout</a>";
	}

}
