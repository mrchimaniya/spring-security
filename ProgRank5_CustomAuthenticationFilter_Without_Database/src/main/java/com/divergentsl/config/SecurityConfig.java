package com.divergentsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.divergentsl.filter.MyBasicCustomAuthenticationFilter;
import com.divergentsl.provider.MyAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthenticationProvider myAuthenticationProvider;
	
	@Autowired
	MyBasicCustomAuthenticationFilter myBasicCustomAuthenticationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.authenticationProvider(myAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(myBasicCustomAuthenticationFilter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().permitAll();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	

	
}
