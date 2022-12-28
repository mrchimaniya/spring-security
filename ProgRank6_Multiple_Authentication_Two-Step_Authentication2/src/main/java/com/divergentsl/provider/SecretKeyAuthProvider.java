package com.divergentsl.provider;

import java.util.Arrays;
import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.divergentsl.auth.SecretKeyAuthToken;
import com.divergentsl.repo.UserSecretKeyRepo;

@Component 
public class SecretKeyAuthProvider implements AuthenticationProvider {

	@Autowired
	UserSecretKeyRepo userSecretKeyRepo;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var userSecretKey=userSecretKeyRepo.findByUsername(authentication.getName());
		
		if(userSecretKey.isPresent() )
	     return new SecretKeyAuthToken(authentication.getName(),authentication.getCredentials(), Arrays.asList(()-> "read"));
	
		throw new BadCredentialsException("Failed User Authentication");
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SecretKeyAuthToken.class.equals(authentication);
	}

}
