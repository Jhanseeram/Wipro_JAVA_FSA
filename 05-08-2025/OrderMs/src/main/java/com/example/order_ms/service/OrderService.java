package com.example.order_ms.service;

import com.example.order_ms.dto.OrderResponse;
import com.example.order_ms.entity.Order;
import com.example.order_ms.model.Payment;
import com.example.order_ms.repository.OrderRepository;
import com.example.order_ms.client.PaymentConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentConnectService paymentConnectService;

    public OrderResponse placeOrder(Order order) {
        order.setOrderStatus("I");
        orderRepository.save(order);  // Save order first

        Payment payment = new Payment();
        payment.setOrderId(order.getId().intValue());
        payment.setPaymentStatus(true);

        try {
            ResponseEntity<Payment> response = paymentConnectService.savePaymentData(payment);
            if (response.getStatusCode().is2xxSuccessful()) {
                order.setOrderStatus("P"); // Paid
                payment = response.getBody();
            } else {
                order.setOrderStatus("C"); // Cancelled
            }
        } catch (Exception e) {
            order.setOrderStatus("C");
        }

        orderRepository.save(order); 

        // Prepare response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setOrderValue(order.getOrderValue());
        orderResponse.setPayment(Collections.singletonList(payment)); 

        return orderResponse;
    }
}
