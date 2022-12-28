package com.divergentsl.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MyCsrfFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		  //here you will get value in attribute no header
		CsrfToken csrfToken = (CsrfToken) request.getAttribute("_user");
		
		System.out.println(csrfToken.getParameterName());
		System.out.println(csrfToken.getToken());
		
		filterChain.doFilter(request, response);
		
	}

}
