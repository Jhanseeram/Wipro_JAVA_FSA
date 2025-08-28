package com.wipro.productmgmtboot.service.impl;

import com.wipro.productmgmtboot.entity.Product;
import com.wipro.productmgmtboot.repo.ProductRepo;
import com.wipro.productmgmtboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = productRepo.findById(id).orElseThrow();
        existing.setName(product.getName());
        existing.setCategory(product.getCategory());
        existing.setPrice(product.getPrice());
        return productRepo.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
