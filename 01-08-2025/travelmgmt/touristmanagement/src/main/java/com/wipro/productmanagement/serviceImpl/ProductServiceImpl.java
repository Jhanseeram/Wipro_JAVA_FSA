package com.wipro.productmanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.productmanagement.repository.ProductRepository;
import com.wipro.productmanagement.service.ProductService;

import productmanagement.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        System.out.println("New Product Created");
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            System.out.println("Product found with ID = " + id);
            return product.get();
        }
        System.out.println("Product not found with ID = " + id);
        return null;
    }

    @Override
    public Product deleteById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            System.out.println("Product deleted with ID = " + id);
            return product.get();
        }
        System.out.println("Product not found with ID = " + id);
        return null;
    }
}
