package com.wipro.productmgmtboot.controller;

import com.wipro.productmgmtboot.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    
    @PersistenceContext
    private EntityManager entityManager;

        // Add logger
        private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductController.class);
    
    @GetMapping
    public List<Product> getAllProducts() {
        try {
            TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
            return query.getResultList();
        } catch (Exception e) {
            // If table doesn't exist, return empty list
            return List.of();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = entityManager.find(Product.class, id);
            return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @Transactional
    public Product createProduct(@RequestBody Product product) {
        log.info("Received product for creation: {}", product);
        try {
            entityManager.persist(product);
            entityManager.flush();
            log.info("Product created successfully: {}", product);
            return product;
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new RuntimeException("Error creating product: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product existingProduct = entityManager.find(Product.class, id);
            if (existingProduct != null) {
                existingProduct.setName(product.getName());
                existingProduct.setCategory(product.getCategory());
                existingProduct.setPrice(product.getPrice());
                return ResponseEntity.ok(entityManager.merge(existingProduct));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new RuntimeException("Error updating product: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            Product product = entityManager.find(Product.class, id);
            if (product != null) {
                entityManager.remove(product);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage());
        }
    }

}
