package com.divergentsoftlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.divergentsoftlabs.helper.JwtUtil;
import com.divergentsoftlabs.model.JwtRequest;
import com.divergentsoftlabs.model.JwtResponse;
import com.divergentsoftlabs.service.CustomUserDetailsService;

@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println(jwtRequest);
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		} 
		
		UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("The Token Is= "+token);
		
		//key=token after colon value  {"token":"value"}
		return ResponseEntity.ok(new JwtResponse(token)); 
	}
}
