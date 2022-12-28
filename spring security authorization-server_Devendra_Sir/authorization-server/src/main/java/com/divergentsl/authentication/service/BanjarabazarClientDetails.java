package com.divergentsl.authentication.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.divergentsl.authentication.model.User;

public class BanjarabazarClientDetails implements UserDetails{ 

	@Autowired
	private User client;
	
	public BanjarabazarClientDetails(User client) {
		this.client = client;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority(this.client.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.client.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.client.getUsername() + "";
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
