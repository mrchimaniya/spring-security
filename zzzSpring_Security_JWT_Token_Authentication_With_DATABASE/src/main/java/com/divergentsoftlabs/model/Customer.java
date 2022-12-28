package com.divergentsoftlabs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Customer {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String email,pwd,role;
   
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
   
   
}