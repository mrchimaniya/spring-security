package com.divergentsl.authentication.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Users")
public class User {
    @Id
	private int username;
	private String password;
	private String customerName;
	private long contactNubmer;
	private String role;
	private int  enabled;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getContactNubmer() {
		return contactNubmer;
	}
	public void setContactNubmer(long contactNubmer) {
		this.contactNubmer = contactNubmer;
	}
	public int getUsername() {
		return username;
	}
	public void setUsername(int username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public User(int username, String password, String customerName, long contactNubmer, String role, int enabled) {
		super();
		this.username = username;
		this.password = password;
		this.customerName = customerName;
		this.contactNubmer = contactNubmer;
		this.role = role;
		this.enabled = enabled;
	}
	
}
