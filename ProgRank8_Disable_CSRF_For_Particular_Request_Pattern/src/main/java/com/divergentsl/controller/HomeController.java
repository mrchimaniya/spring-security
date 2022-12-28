package com.divergentsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  
	 @GetMapping("/home")
	 public String hello()
	 {
		 return "home.html";
	 }
	 
	 //generate same other html form just like
	 
/*	 in this form we have save full url and anybody can send same data with the outsider/bug form because of csrf disable
	 <html>
	  <body>
	  <h1>Home Welcome</h1>
	    <form action="http://localhost:8080/savedata" method="post">
	      <input name="uname"/>
	      <input type="submit" value="SAVE DATA"/>
	    </form> 	  
	 </body>
	</html>  */
	 
	 @PostMapping("/savedata")
	 public String save(String uname)
	 {
		 System.out.println(uname);
		 return "home.html";
	 }	 
	 
	 @GetMapping("good")
	 public String good()
	 {
		 return "good.html";
	 }
	 
	 @PostMapping("/abc/good")
	 public String getgood(String uname)
	 {
		 System.out.println(uname);
		 return "good.html";
	 }
	 
	 

}
