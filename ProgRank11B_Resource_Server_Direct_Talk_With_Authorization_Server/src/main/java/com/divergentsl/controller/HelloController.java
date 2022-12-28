package com.divergentsl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
	    
		return "Hello!  Here resource server is using Interospection Means Direct Validate Token With Authrization Server";
	}
	
	/* Go and look properties file
	 * here resource server using "Interospection"
	 * means for token validation resource server is directly talking to authorization server
	 */
}

