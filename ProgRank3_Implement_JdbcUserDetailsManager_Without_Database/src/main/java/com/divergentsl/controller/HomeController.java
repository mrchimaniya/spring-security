package com.divergentsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.divergentsl.dto.User;
import com.divergentsl.service.MyUserDetailsService;

@RestController
public class HomeController {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	//here could have used direct user details manager
	//for saving user but we are using propers principals
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello")
	public String hello()
	{
		return "<h1>Hello Welcome</h1><br><a href='/logout'>Logout</a>";
	}
	
	@PostMapping("/createUser")
	public void createUser(@RequestBody User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		myUserDetailsService.ourCreateUser(user);
	}

}
