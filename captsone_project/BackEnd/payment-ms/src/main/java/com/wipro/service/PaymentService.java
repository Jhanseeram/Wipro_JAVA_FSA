package com.wipro.service;

import com.wipro.controller.PaymentController.PaymentRequest;
import com.wipro.controller.PaymentController.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    private Map<String, String> paymentStatuses = new HashMap<>();

    public PaymentResponse processDirectPayment(PaymentRequest request) {
        try {
            // Generate payment ID
            String paymentId = "PAY_" + UUID.randomUUID().toString().substring(0, 8);
            String transactionId = "TXN_" + UUID.randomUUID().toString().substring(0, 8);

            // Simulate payment processing (90% success rate like in PaymentProcessingService)
            boolean isSuccessful = Math.random() < 0.9;
            
            String status = isSuccessful ? "SUCCESS" : "FAILED";
            String message = isSuccessful ? "Payment processed successfully" : "Payment processing failed";

            // Store payment status
            paymentStatuses.put(paymentId, status);

            return new PaymentResponse(paymentId, status, message, transactionId);

        } catch (Exception e) {
            String errorPaymentId = "PAY_ERROR_" + UUID.randomUUID().toString().substring(0, 8);
            paymentStatuses.put(errorPaymentId, "FAILED");
            return new PaymentResponse(errorPaymentId, "FAILED", "Payment processing error: " + e.getMessage(), null);
        }
    }

    public String getPaymentStatus(String paymentId) {
        return paymentStatuses.getOrDefault(paymentId, "NOT_FOUND");
    }
}