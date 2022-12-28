package com.divergentsl.repo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

public class MyCustomCsrfTokenRepo implements CsrfTokenRepository
{

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
	  CsrfToken csrfToken=new DefaultCsrfToken("X-CSRF-TOKEN", "_user","abcdefg123456");
	  return csrfToken;
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}