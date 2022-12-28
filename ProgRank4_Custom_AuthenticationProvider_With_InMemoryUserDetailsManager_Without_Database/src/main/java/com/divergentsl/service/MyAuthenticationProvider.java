package com.divergentsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component 
public class MyAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//client input credentials
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		 var user = userDetailsService.loadUserByUsername(username);
		 
		 if(user!=null && passwordEncoder.matches(password, user.getPassword()))
              return  new UsernamePasswordAuthenticationToken(username, password);
		
		 return (Authentication) new BadCredentialsException("Error Found!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

	
}
