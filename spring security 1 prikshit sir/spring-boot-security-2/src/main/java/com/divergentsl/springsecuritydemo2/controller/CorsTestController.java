package com.divergentsl.springsecuritydemo2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/cors-test")
public class CorsTestController {
	
	@GetMapping
	public String main(Authentication a) {
		log.debug("inside main");
		//ModelAndView model = new ModelAndView("main.html");
		return "cors-test.html";
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<String> postResponse(Authentication a) {
		log.debug("inside postResponse");
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
}
