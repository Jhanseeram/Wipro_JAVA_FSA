package com.example.order_ms.controller;

import com.example.order_ms.dto.OrderResponse;
import com.example.order_ms.entity.Order;
import com.example.order_ms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody Order order) {
        OrderResponse response = orderService.placeOrder(order);
        return ResponseEntity.ok(response);
    }
}
