package com.divergentsl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "secretkey")
public class UserSecretKey {
    
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 private String key;
	 private String username;
	
	 
	@Override
	public String toString() {
		return "UserSecretKey [id=" + id + ", username=" + username + ", key=" + key + "]";
	}
	 
	 
	  
}
