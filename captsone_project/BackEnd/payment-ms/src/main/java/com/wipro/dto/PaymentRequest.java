package com.wipro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentRequest {
    
    @JsonProperty("bookingId")
    private String bookingId;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    
    @JsonProperty("customerEmail")
    private String customerEmail;
    
    @JsonProperty("customerName")
    private String customerName;
    
    @JsonProperty("flightDetails")
    private String flightDetails;
    
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    // Default constructor
    public PaymentRequest() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor
    public PaymentRequest(String bookingId, BigDecimal amount, String paymentMethod, 
                         String customerEmail, String customerName, String flightDetails) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.flightDetails = flightDetails;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(String flightDetails) {
        this.flightDetails = flightDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "bookingId='" + bookingId + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerName='" + customerName + '\'' +
                ", flightDetails='" + flightDetails + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
