package com.divergentsl.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin();
		
		
//		other way is using annotation in controller class
		http.cors().configurationSource(
				 c->{
					 CorsConfiguration cc=new CorsConfiguration();
					 cc.setAllowedOrigins(Arrays.asList("*"));
					 cc.setAllowedMethods(Arrays.asList("GET","POST"));
					 return cc;
				 }
				);
	}
}
