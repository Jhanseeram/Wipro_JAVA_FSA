package com.wipro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {
    
    @JsonProperty("bookingId")
    private String bookingId;
    
    @JsonProperty("paymentId")
    private String paymentId;
    
    @JsonProperty("status")
    private String status; // SUCCESS, FAILED, PENDING
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    // Default constructor
    public PaymentResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor
    public PaymentResponse(String bookingId, String paymentId, String status, 
                          BigDecimal amount, String message) {
        this.bookingId = bookingId;
        this.paymentId = paymentId;
        this.status = status;
        this.amount = amount;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "bookingId='" + bookingId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
