package com.example.payment_ms.controller;

import com.example.payment_ms.entity.Payment;
import com.example.payment_ms.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return service.savePayment(payment);
    }

    @GetMapping("/orders/{orderId}")
    public List<Payment> getPaymentsByOrderId(@PathVariable Long orderId) {
        return service.getPaymentsByOrderId(orderId);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }
}
