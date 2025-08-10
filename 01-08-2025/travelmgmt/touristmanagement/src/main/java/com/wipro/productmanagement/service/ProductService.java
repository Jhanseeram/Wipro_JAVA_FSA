package com.wipro.productmanagement.service;

//import java.util.ArrayList;
import java.util.List;


import productmanagement.entity.Product;

public interface ProductService {
	
	void save(Product product);
//	List<Product> product = new ArrayList<>();
	
	List<Product> findAll();
	
	Product findById(int id);

	
	Product deleteById(int id);

}
