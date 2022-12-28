package com.divergentsl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
	    
		return "Hello!  Here resource server is using BlackBoarding Means Resource Server Is Taking Data From D/B";
	}
	
}

