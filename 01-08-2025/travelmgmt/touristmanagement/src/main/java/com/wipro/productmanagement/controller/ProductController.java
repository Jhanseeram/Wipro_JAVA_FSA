package com.wipro.productmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.productmanagement.service.ProductService;

import productmanagement.entity.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public void save(@RequestBody Product product) {
        System.out.println("New Product Created from controller");
        productService.save(product);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product!=null) {
            System.out.println("Product found by id: " + id + " from controller");
            return product;
        } else {
            System.out.println("Product not found by id: " + id + " from controller");
            return null; // or throw a custom exception
        }
    }


    @DeleteMapping("/{id}")
    public Product deleteById(@PathVariable int id) {
        Product product = productService.deleteById(id);
        if (product != null) {
            System.out.println("Product deleted by id: " + id + " from controller");
            return product;
        } else {
            System.out.println("Product not found by id: " + id + " from controller");
            return null;
        }
    }

    @PutMapping
    public void update(@RequestBody Product product) {
        System.out.println("Product Updated from controller");
        productService.save(product);
    }
}
