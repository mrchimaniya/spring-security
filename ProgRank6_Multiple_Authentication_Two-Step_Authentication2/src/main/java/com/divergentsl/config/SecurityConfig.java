package com.divergentsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.divergentsl.filter.TokenAuthFilter;
import com.divergentsl.filter.UserPasswordAuthFilter;
import com.divergentsl.provider.SecretKeyAuthProvider;
import com.divergentsl.provider.TokenAuthProvider;
import com.divergentsl.provider.UserPasswordAuthProvider;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	
	@Autowired
	UserPasswordAuthFilter userPasswordAuthFilter;
	
	@Autowired
	UserPasswordAuthProvider userPasswordAuthProvider;
	
	@Autowired
	SecretKeyAuthProvider secretKeyAuthProvider;
	
//	@Autowired
//	TokenAuthFilter tokenAuthFilter;  because of ciruclar depency
	
	@Autowired
	TokenAuthProvider tokenAuthProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userPasswordAuthProvider)
		    .authenticationProvider(secretKeyAuthProvider)
		    .authenticationProvider(tokenAuthProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(userPasswordAuthFilter,BasicAuthenticationFilter.class)
		.addFilterBefore(/*tokenAuthFilter*/ tokenAuthFilter(), BasicAuthenticationFilter.class);
	}
	
	@Bean
	public TokenAuthFilter tokenAuthFilter()
	{
		return new TokenAuthFilter();
	}
	
	 
}