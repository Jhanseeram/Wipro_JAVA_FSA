package com.example.productmgmt.service;

import com.example.productmgmt.entity.ProductOrder;

import java.util.List;

public interface OrderService {
    ProductOrder placeOrder(Long productId, Integer qty);
    List<ProductOrder> history();
}
