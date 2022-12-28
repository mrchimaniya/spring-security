package com.divergentsl.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManageConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService()
	{
		InMemoryUserDetailsManager user=new InMemoryUserDetailsManager();
		user.createUser(User.withUsername("user")
				            .password(passwordEncoder().encode("123"))
				            .authorities("read")
				            .build());
		return user;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
	   return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Bean
//	public DataSource dataSource() {
//       return new Drivermana
//	}
//	  
	
}
