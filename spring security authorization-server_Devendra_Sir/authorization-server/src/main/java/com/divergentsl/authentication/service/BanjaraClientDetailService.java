package com.divergentsl.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.divergentsl.authentication.Repo.ClientRepo;
import com.divergentsl.authentication.model.User;

@Service
public class BanjaraClientDetailService implements UserDetailsService{

	@Autowired
	ClientRepo clientRepo;
	  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User client = this.clientRepo.findByUsername(Integer.parseInt(username)).get();
		
		if( client == null) {
			throw new UsernameNotFoundException("Bad Credentials...");
		}
		
		return new BanjarabazarClientDetails(client);
	}


}
