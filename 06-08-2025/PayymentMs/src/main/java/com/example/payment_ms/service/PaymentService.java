package com.example.payment_ms.service;

import com.example.payment_ms.entity.Payment;
import com.example.payment_ms.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment savePayment(Payment payment) {
        return repository.save(payment);
    }

    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }
}
