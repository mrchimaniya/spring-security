package com.divergentsl.springsecuritydemo2.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private double price;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	public Product(String name, Currency cur) {
		this.name = name;
		this.currency = cur;
	}
	
	public Product() {
		
	}

}
