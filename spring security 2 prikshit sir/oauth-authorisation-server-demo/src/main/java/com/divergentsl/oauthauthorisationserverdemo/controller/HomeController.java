package com.divergentsl.oauthauthorisationserverdemo.controller;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.divergentsl.oauthauthorisationserverdemo.http.RestTemplateFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	RestTemplateFactory restTemplateFactory;

	@GetMapping("/authorization")
	public ModelAndView authorizationCode(Authentication auth, @RequestParam("code") String code) {
		log.debug("Code : {} // Auth :{}", code, auth);
		
		ModelAndView model = new ModelAndView("authorization.html");
		model.addObject("code", code);
		
		// REST call to the authorization server for getting the token
		restTemplateFactory.getObject().getInterceptors().add(new BasicAuthorizationInterceptor("client1", "secret1"));
		ResponseEntity<Map> map = restTemplateFactory.getObject().exchange("http://localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code="+code,
				HttpMethod.POST, null, Map.class);

		log.debug("response {}" , map.getBody().get("access_token"));
		
		model.addObject("access_token", map.getBody().get("access_token"));
		
		return model;
	}

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	
	@GetMapping("/password")
	public ModelAndView password(Authentication auth, @RequestParam("code") String code) {
		ModelAndView model = new ModelAndView("password.html");
		return model;
	}
	
}
