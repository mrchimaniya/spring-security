package com.divergentsoftlabs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.divergentsoftlabs.model.Customer;
import com.divergentsoftlabs.repo.CustomerRepo;

@Service 
public class CustomUserDetailsService implements UserDetailsService{
	
	//@Autowired(required = true)
	@Autowired
	CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Customer> customer=customerRepo.findByEmail(username);
		if(customer.size()==0)
		{
			throw new UsernameNotFoundException("User Not Found: "+username);
		}
		return new CustomerUserDetails(customer.get(0));
	}
 
}
