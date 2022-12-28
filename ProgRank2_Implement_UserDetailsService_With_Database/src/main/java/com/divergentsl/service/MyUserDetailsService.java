package com.divergentsl.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.divergentsl.enitity.User;
import com.divergentsl.repo.UserRepo;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepo.findByUsername(username);
		
		User u=user.orElseThrow( ()-> new UsernameNotFoundException("User Not Found") );
		return new MyUserDetails(u);
	}
	
		

}
