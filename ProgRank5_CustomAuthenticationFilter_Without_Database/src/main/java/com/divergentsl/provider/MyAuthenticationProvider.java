package com.divergentsl.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.divergentsl.filter.MyCustomAuthenticationToken;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Value( "${secret_key}" )
	String value;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	     String secret_key=authentication.getName();
	     if(secret_key.equals(value))
	    	 return new MyCustomAuthenticationToken(null, null,null);
	     return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MyCustomAuthenticationToken.class.equals(authentication);
	}

}
