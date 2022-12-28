package com.divergentsl.springbootsecurity.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.authenticationProvider(authenticationProvider);

		var userDetailsService = new InMemoryUserDetailsManager();
		var user = User.withUsername("john").password("12345").authorities("read").build();

		userDetailsService.createUser(user);
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(List.of("*"));
				config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
				return config;
			};
			c.configurationSource(source);
		});

		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();

	}

}
