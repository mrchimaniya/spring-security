package com.divergentsl.springsecuritydemo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divergentsl.springsecuritydemo2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


}
