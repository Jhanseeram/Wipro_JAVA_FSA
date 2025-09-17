package com.wipro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wipro.service.PaymentService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.processDirectPayment(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment processing failed: " + e.getMessage());
        }
    }

    @GetMapping("/status/{paymentId}")
    public ResponseEntity<?> getPaymentStatus(@PathVariable String paymentId) {
        try {
            String status = paymentService.getPaymentStatus(paymentId);
            return ResponseEntity.ok(new PaymentStatusResponse(paymentId, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to fetch payment status");
        }
    }

    // Request/Response DTOs
    public static class PaymentRequest {
        private String bookingId;
        private String customerName;
        private String customerEmail;
        private BigDecimal amount;
        private String paymentMethod;
        private String cardNumber;
        private String expiryDate;
        private String cvv;

        // Getters and setters
        public String getBookingId() { return bookingId; }
        public void setBookingId(String bookingId) { this.bookingId = bookingId; }
        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }
        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getExpiryDate() { return expiryDate; }
        public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
    }

    public static class PaymentResponse {
        private String paymentId;
        private String status;
        private String message;
        private String transactionId;

        public PaymentResponse(String paymentId, String status, String message, String transactionId) {
            this.paymentId = paymentId;
            this.status = status;
            this.message = message;
            this.transactionId = transactionId;
        }

        // Getters
        public String getPaymentId() { return paymentId; }
        public String getStatus() { return status; }
        public String getMessage() { return message; }
        public String getTransactionId() { return transactionId; }
    }

    public static class PaymentStatusResponse {
        private String paymentId;
        private String status;

        public PaymentStatusResponse(String paymentId, String status) {
            this.paymentId = paymentId;
            this.status = status;
        }

        // Getters
        public String getPaymentId() { return paymentId; }
        public String getStatus() { return status; }
    }
}