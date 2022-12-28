package com.divergentsl.springsecuritydemo2.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.divergentsl.springsecuritydemo2.service.JpaUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	@Autowired
	JpaUserDetailsService jpaUserDetailsService;

	/*
	 * @Autowired private AuthenticationProvider authenticationProvider;
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Bean public UserDetailsService userDetailService(DataSource datasource) {
	 * return new JdbcUserDetailsManager(datasource); }
	 */
	@Bean
	public SCryptPasswordEncoder sCryptPasswordEncoder() {
		return new SCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		//auth.authenticationProvider(authenticationProvider);

		try {
			auth.userDetailsService(jpaUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable();
		http.formLogin().defaultSuccessUrl("/product", true);
		http.authorizeRequests().mvcMatchers("/product/**").authenticated();
		//http.authorizeRequests().anyRequest().permitAll();
		
	}

}
