package com.divergentsl.springsecuritydemo2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergentsl.springsecuritydemo2.entity.Product;
import com.divergentsl.springsecuritydemo2.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Transactional
	public void add(Product p) {
		productRepository.save(p);
	}
	
}
