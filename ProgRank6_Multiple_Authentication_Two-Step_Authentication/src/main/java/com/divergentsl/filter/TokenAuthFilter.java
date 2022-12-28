package com.divergentsl.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.divergentsl.auth.TokenAuthentication;

//@Component  because it is generation ciruclar dependency error so we will use java based config class
public class TokenAuthFilter extends OncePerRequestFilter {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var tokenNo=request.getHeader("Authorization");
		
		//creating authentication object using sub class of usernameauthenticationtoken class 
		Authentication authentication=new TokenAuthentication(tokenNo,null);
		
		//delegating req to the authentication manager and it will delegate to authentication provider
		var a=authenticationManager.authenticate(authentication);
		
		//finally save the token to the security context manager
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return request.getServletPath().equals("/login");
	}

}
