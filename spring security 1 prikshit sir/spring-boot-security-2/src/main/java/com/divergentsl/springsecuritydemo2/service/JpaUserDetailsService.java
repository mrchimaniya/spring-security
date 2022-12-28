package com.divergentsl.springsecuritydemo2.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.divergentsl.springsecuritydemo2.entity.User;
import com.divergentsl.springsecuritydemo2.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) {
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
		//Optional if the user does not exist otherwise, it returns the User instance
		User u = userRepository.findUserByUsername(username).orElseThrow(s);
		return new CustomUserDetails(u);
	}

}
