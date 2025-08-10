package com.wipro.productmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.wipro.productmanagement.entity.Product;
import com.wipro.productmanagement.service.ProductService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "Product Management APIs")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Save a Product")
	  @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Saved successfully"),
	    @ApiResponse(responseCode = "404", description = "Not found")
	  })
    @PostMapping
    public String save(@RequestBody Product product) {
        productService.save(product);
        return "New product created";
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        Product deleted = productService.deleteById(id);
        if (deleted != null) {
            return "Product deleted with ID: " + id;
        } else {
            return "Product not found with ID: " + id;
        }
    }

    @PutMapping
    public String update(@RequestBody Product product) {
        productService.save(product);
        return "Product updated";
    }

    @GetMapping("/make/{productMakeType}")
    public List<Product> findByProductMake(@PathVariable String productMakeType) {
        return productService.findByProductMake(productMakeType);
    }

    @GetMapping("/make/{productMake}/{productName}")
    public List<Product> findByProductMakeAndName(@PathVariable String productMake, @PathVariable String productName) {
        return productService.findByProductMakeAndProductName(productMake, productName);
    }

   
    @GetMapping("/productName/desc")
    public List<Product> getProductsSortedByNameDesc() {
        return productService.findAllByOrderByProductNameDesc();
    }
    
    @GetMapping("/page/{pageNum}/{pageSize}/{sortOrder}")
    public Page<Product> findAllPage(@PathVariable int pageNum,
                                     @PathVariable int pageSize,
                                     @PathVariable int sortOrder) {
        Pageable pageable;

        if (sortOrder == 0) {
            pageable = PageRequest.of(pageNum, pageSize, Sort.by("productPrice").descending());
        } else {
            pageable = PageRequest.of(pageNum, pageSize, Sort.by("productPrice").ascending());
        }

        return productService.findAll(pageable);
    }

 
}
