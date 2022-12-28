package com.divergentsl.oauthsecuritythirdparty.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	@GetMapping("/")
	public String main(OAuth2AuthenticationToken token) {
		
		log.info(String.valueOf(token.getPrincipal()));

		return "main.html";
	}
	
}
