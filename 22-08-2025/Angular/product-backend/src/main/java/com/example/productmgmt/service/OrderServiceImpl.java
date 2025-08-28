package com.example.productmgmt.service;

import com.example.productmgmt.entity.Product;
import com.example.productmgmt.entity.ProductOrder;
import com.example.productmgmt.repo.ProductOrderRepository;
import com.example.productmgmt.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final ProductOrderRepository orderRepository;

    public OrderServiceImpl(ProductRepository productRepository, ProductOrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public ProductOrder placeOrder(Long productId, Integer qty) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (qty == null || qty <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (product.getQty() == null) product.setQty(0);
        if (product.getQty() < qty) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + product.getQty());
        }
        product.setQty(product.getQty() - qty);
        productRepository.save(product);
        ProductOrder order = new ProductOrder(product, qty);
        return orderRepository.save(order);
    }

    @Override
    public List<ProductOrder> history() {
        return orderRepository.findAll();
    }
}
