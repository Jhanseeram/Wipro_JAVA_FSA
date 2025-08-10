package com.wipro.productmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.productmanagement.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductMake(String productMake);

    List<Product> findByProductMakeAndProductName(String productMake, String productName);

    List<Product> findAllByOrderByProductNameDesc(); 
    
    Page<Product>  findAll(Pageable p);
}
