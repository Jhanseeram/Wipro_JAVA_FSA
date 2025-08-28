package com.example.productmgmt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    private Integer qtyPurchased;
    private LocalDateTime orderDate;

    public ProductOrder() {}

    public ProductOrder(Product product, Integer qtyPurchased) {
        this.product = product;
        this.qtyPurchased = qtyPurchased;
        this.orderDate = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (orderDate == null) orderDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQtyPurchased() { return qtyPurchased; }
    public void setQtyPurchased(Integer qtyPurchased) { this.qtyPurchased = qtyPurchased; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}
