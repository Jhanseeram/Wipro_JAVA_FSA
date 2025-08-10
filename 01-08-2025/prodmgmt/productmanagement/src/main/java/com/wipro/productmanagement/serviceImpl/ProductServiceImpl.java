package com.wipro.productmanagement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wipro.productmanagement.entity.Product;
import com.wipro.productmanagement.repository.ProductRepository;
import com.wipro.productmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product deleteById(int id) {
        Product product = findById(id);
        if (product != null) {
            productRepository.deleteById(id);
        }
        return product;
    }

    @Override
    public List<Product> findByProductMake(String productMake) {
        return productRepository.findByProductMake(productMake);
    }

    @Override
    public List<Product> findByProductMakeAndProductName(String productMake, String productName) {
        return productRepository.findByProductMakeAndProductName(productMake, productName);
    }

    @Override
    public List<Product> findAllByOrderByProductNameDesc() {
        return productRepository.findAllByOrderByProductNameDesc();
    }
    
    @Override
	public Page<Product> findAll(Pageable p) {
		// TODO Auto-generated method stub
		return productRepository.findAll(p);
	}
}
