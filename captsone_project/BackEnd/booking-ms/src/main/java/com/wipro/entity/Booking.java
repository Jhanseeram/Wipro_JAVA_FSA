package com.wipro.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "booking_id", nullable = false, unique = true)
    private String bookingId;
    
    @Column(name = "passenger_name", nullable = false)
    private String passengerName;
    
    @Column(name = "passenger_email", nullable = false)
    private String passengerEmail;
    
    @Column(name = "passenger_phone")
    private String passengerPhone;
    
    @Column(name = "passenger_age", nullable = false)
    private int passengerAge;
    
    @Column(name = "passenger_gender")
    private String passengerGender;
    
    @Column(name = "flight_id", nullable = false)
    private Long flightId;
    
    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;
    
    @Column(name = "amount", nullable = false)
    private double amount;
    
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;
    
    @Column(name = "payment_id")
    private String paymentId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status = BookingStatus.INITIATED;
    
    @Column(name = "pnr")
    private String pnr;
    
    @Column(name = "e_ticket_number")
    private String eTicketNumber;
    
    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "failure_reason")
    private String failureReason;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    // Enums
    public enum BookingStatus {
        INITIATED, PAYMENT_PENDING, CONFIRMED, FAILED, CANCELLED
    }
    
    public enum PaymentStatus {
        PENDING, PROCESSING, SUCCESS, FAILED
    }
    
    // Constructors
    public Booking() {}
    
    public Booking(String bookingId, String passengerName, String passengerEmail, 
                   String passengerPhone, int passengerAge, Long flightId, 
                   LocalDate travelDate, double amount, String paymentMethod) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerPhone = passengerPhone;
        this.passengerAge = passengerAge;
        this.flightId = flightId;
        this.travelDate = travelDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    
    public String getPassengerEmail() { return passengerEmail; }
    public void setPassengerEmail(String passengerEmail) { this.passengerEmail = passengerEmail; }
    
    public String getPassengerPhone() { return passengerPhone; }
    public void setPassengerPhone(String passengerPhone) { this.passengerPhone = passengerPhone; }
    
    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }
    
    public String getPassengerGender() { return passengerGender; }
    public void setPassengerGender(String passengerGender) { this.passengerGender = passengerGender; }
    
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    
    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { 
        this.status = status; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
    
    public String getETicketNumber() { return eTicketNumber; }
    public void setETicketNumber(String eTicketNumber) { this.eTicketNumber = eTicketNumber; }
    
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    
    public String getFailureReason() { return failureReason; }
    public void setFailureReason(String failureReason) { this.failureReason = failureReason; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}