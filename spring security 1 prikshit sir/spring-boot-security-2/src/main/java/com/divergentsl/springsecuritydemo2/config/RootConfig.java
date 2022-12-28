package com.divergentsl.springsecuritydemo2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.divergentsl.springsecuritydemo2"})
@EnableWebMvc
public class RootConfig {
	


}
