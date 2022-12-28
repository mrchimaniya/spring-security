package com.divergentsl.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;  

@Component
public class MyBasicCustomAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	AuthenticationManager manager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//steps as same as work like basic authentication filter
		
	    // based upon request
		String authObj=request.getHeader("auth_key");
				
	    //create authentication object just like UserNamePasswordAuthenticationToken
		    MyCustomAuthenticationToken objToken = new MyCustomAuthenticationToken(authObj, null);
//		    UsernamePasswordAuthenticationToken objToken = new UsernamePasswordAuthenticationToken(authObj, null);
		
	    //delegate obj to AuthenticationManager then authenticationmanager will go --> AuthenticationProvider
		  try {
			Authentication authPrincipal = manager.authenticate(objToken);
			
			//this process is done when the request is coming back from the authentication manager by diagram flow
   		  //for future use save principal into security context
			  if(authPrincipal.isAuthenticated())
			  {
				  SecurityContextHolder.getContext().setAuthentication(authPrincipal);
				  filterChain.doFilter(request,response);
			  }
		 } catch (AuthenticationException e) {
			 throw new BadCredentialsException("Invalid Pricipal");
	     	}
    }
	

}
