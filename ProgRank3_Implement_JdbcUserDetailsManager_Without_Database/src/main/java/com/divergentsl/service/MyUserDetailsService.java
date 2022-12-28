package com.divergentsl.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.divergentsl.dto.User;

@Service
public class MyUserDetailsService {
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	public void ourCreateUser(User user)
	{
		userDetailsManager.createUser(user);
	}
		

}
