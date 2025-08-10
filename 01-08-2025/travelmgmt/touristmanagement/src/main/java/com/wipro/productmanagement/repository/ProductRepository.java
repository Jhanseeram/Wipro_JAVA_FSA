package com.wipro.productmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import productmanagement.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
