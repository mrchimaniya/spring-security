package com.divergentsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

import com.divergentsl.filter.MyCsrfFilter;
import com.divergentsl.repo.MyCustomCsrfTokenRepo;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MyCsrfFilter myCsrfFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	 //if we disable csrf then only we can send data on server

		//http.csrf().disable();
		http.csrf(csrf->csrf.csrfTokenRepository(new MyCustomCsrfTokenRepo()));
		http.authorizeRequests().anyRequest().authenticated();
		http.httpBasic();
		
		http.addFilterAfter(myCsrfFilter,CsrfFilter.class);
	}

}
