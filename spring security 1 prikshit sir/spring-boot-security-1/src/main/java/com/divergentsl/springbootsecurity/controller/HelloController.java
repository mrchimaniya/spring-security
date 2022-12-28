package com.divergentsl.springbootsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello!";
	}
	
	@PostMapping("/hello")
	@ResponseBody
	public String helloPost() {
		return "Hello!";
	}
	
}
