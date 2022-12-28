package com.divergentsl.springsecuritydemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.divergentsl.springsecuritydemo2.entity.Currency;
import com.divergentsl.springsecuritydemo2.entity.Product;
import com.divergentsl.springsecuritydemo2.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class MainPageController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ModelAndView main(Authentication a) {
		ModelAndView model = new ModelAndView("main.html");
		model.addObject("username", a.getName());
		model.addObject("products", productService.findAll());
		return model;
	}

	@PostMapping("/add")
	public String add(@RequestParam String name) {
		log.info("Adding product " + name);
		//TODO add through service in the database
		productService.add(new Product(name, Currency.EUR));
		return "redirect:/product";
	}
	
}
