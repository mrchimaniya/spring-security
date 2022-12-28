package com.divergentsl.filter;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.divergentsl.auth.SecretKeyAuthToken;
import com.divergentsl.auth.UsernamePasswordAuthToken;
import com.divergentsl.model.UserSecretKey;
import com.divergentsl.repo.TokenManagerRepo;
import com.divergentsl.repo.UserSecretKeyRepo;


@Component
//it is like basic authentication filter
public class UserPasswordAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	TokenManagerRepo tokenManager;
	
	@Autowired
	UserSecretKeyRepo userSecretKeyRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var uname=request.getHeader("uname");
		var password=request.getHeader("password");
       //*********two step authentication********
	   //first step is authenticate using username & password
	   //second step is authenticate using secret key
		var key=request.getHeader("secret_key");
		
	   //it means user will not sent secret key in first time
	   //thats why we are using key==null so we will authenticate user by its username and password
		if(key==null) 
		{   //with uid and password
			var auth=manager.authenticate(new UsernamePasswordAuthToken(uname, password)); //delegate to AuthenticationManager
			
			 //save generated key into db
			//just imagine this key is also going to his mobile number as a otp
			UserSecretKey secretKey=new UserSecretKey();
			
			secretKey.setKey( new Random().nextInt(999)*1000+"");
			secretKey.setUsername(uname);
			
			userSecretKeyRepo.save(secretKey);
		}
		else
		{
			//if key is not null so we well authenticate user through the key
			var auth= manager.authenticate(new SecretKeyAuthToken(uname, key));
			
			//generate a token
			//here unique token is genrating using uuid
			//          tokenManager.add(UUID.randomUUID().toString());****
			String randomToken=UUID.randomUUID().toString();
			tokenManager.add(randomToken);
			
			 response.setHeader("AuthenticationToken",randomToken); 
			 //infuture we will keep this token in db
			 //but now we will work on inmemory level
			
		}
		
		  
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}

}
