package com.divergentsl.dto;

import java.util.Collection; 
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class User implements UserDetails{
	
   private String username;
   private String password;
   
   
   public void setUsername(String username) {
	this.username = username;
    }
   
   public void setPassword(String password) {
	this.password = password;
    }
   
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	 return List.of(()-> "read");
	 
	 //here every user will take the same authority because we have hardcoded it that's why
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
@Override
public String getPassword() {
	// TODO Auto-generated method stub
	return this.password;
}
@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return this.username;
}

@Override
public String toString() {
	return "User [username=" + username + ", password=" + password + "]";
}


}
