package com.divergentsl.springbootsecuritydemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	DataSource dataSource;

	// Overriding User Detail Service

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// Authorization configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// using basic authentication
		http.formLogin();
		// All the request require authentication because anyRequest() is used
		http.authorizeRequests().anyRequest().authenticated();

		// Giving access to a particular url/resource to a use with particular authority
		//http.authorizeRequests().antMatchers("/hello").hasAuthority("read");

		// None of the requests need to be authenticated.
		// http.authorizeRequests().anyRequest().permitAll();

	}

	// Authentication configuration

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//auth.userDetailsService(userDetailsService(dataSource)).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider);
	}

}
