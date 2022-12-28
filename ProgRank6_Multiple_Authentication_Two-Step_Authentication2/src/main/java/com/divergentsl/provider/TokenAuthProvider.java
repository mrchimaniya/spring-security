 package com.divergentsl.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.divergentsl.auth.TokenAuthentication;
import com.divergentsl.repo.TokenManagerRepo;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

	@Autowired 
	private TokenManagerRepo tokenManagerRepo; //it is like repo of token
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String tokenNo=authentication.getName();
		boolean flag=tokenManagerRepo.contains(tokenNo);
		if(flag)
		  return new TokenAuthentication(tokenNo,null,null);
		throw new BadCredentialsException("!Failed");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.equals(authentication);
	}

}
