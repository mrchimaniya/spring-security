package com.divergentsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@CrossOrigin("*")
public class HomeController {
  
	 @GetMapping("/myapp")
	 public String hello()
	 {
		 return "home.html";
	 }
	 
	 @GetMapping("/accessData")
	 @ResponseBody
//	 @CrossOrigin("*")        or otherway is in securityconfig class
//	 @CrossOrigin("http://localhost:8083")  you can use this annotation at class level so all resource will be accesable
	 public String other()
	 {
		 return "Hello Acessable Data";
	 }
	
}
