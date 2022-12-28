package com.divergentsl.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.divergentsl.authentication.Repo.ClientRepo;
import com.divergentsl.authentication.model.User;
import com.divergentsl.authentication.service.BanjarabazarClientDetails;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements UserDetailsService{

	@Autowired
	UserDetailsService userDetailsService;
	
//	UserDetailsService detailsService() {
//		var inn = new InMemoryUserDetailsManager();
//		inn.createUser(User.withUsername("amogh").password("amogh").authorities("read").build());
//		return inn;
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	ClientRepo clientRepo;
	  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User client = this.clientRepo.findByUsername(Integer.parseInt(username)).get();
		
		if( client == null) {
			throw new UsernameNotFoundException("Bad Credentials...");
		}
		
		return new BanjarabazarClientDetails(client);
	}

}
