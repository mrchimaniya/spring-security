package com.divergentsl.auth;

import java.util.Collection; 

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuthToken extends UsernamePasswordAuthenticationToken {

	public UsernamePasswordAuthToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
	}

	public UsernamePasswordAuthToken(Object principal, Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}

}
