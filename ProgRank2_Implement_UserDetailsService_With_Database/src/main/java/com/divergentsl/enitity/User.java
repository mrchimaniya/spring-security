package com.divergentsl.enitity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ouruser")
public class User {
	
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String username;
   private String password;
}
