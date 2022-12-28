package com.divergentsl.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.divergentsl.auth.UsernamePasswordAuthToken;
import com.divergentsl.service.MyUserDetailsService;

@Component
public class UserPasswordAuthProvider implements AuthenticationProvider {

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    var user=myUserDetailsService.loadUserByUsername(authentication.getName());
	    
	    if(passwordEncoder.matches(authentication.getCredentials().toString() ,user.getPassword())) 
	    	 return new UsernamePasswordAuthToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		
	    throw new BadCredentialsException("Failed Authentication");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthToken.class.equals(authentication);
	}

}
