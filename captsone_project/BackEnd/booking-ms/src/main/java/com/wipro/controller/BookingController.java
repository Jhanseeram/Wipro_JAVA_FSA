package com.wipro.controller;

import com.wipro.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            logger.info("Received booking request for customer: {}", request.getCustomerName());

            String bookingId = bookingService.createBookingAndInitiatePayment(
                request.getCustomerName(),
                request.getCustomerEmail(),
                request.getFlightDetails(),
                request.getAmount(),
                request.getPaymentMethod()
            );

            BookingResponse response = new BookingResponse(bookingId, "PENDING", "Booking created and payment initiated");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error creating booking", e);
            return ResponseEntity.badRequest().body("Failed to create booking: " + e.getMessage());
        }
    }

    @GetMapping("/{bookingId}/status")
    public ResponseEntity<?> getBookingStatus(@PathVariable String bookingId) {
        try {
            String status = bookingService.getBookingStatus(bookingId);
            return ResponseEntity.ok(new BookingStatusResponse(bookingId, status));
        } catch (Exception e) {
            logger.error("Error fetching booking status for: {}", bookingId, e);
            return ResponseEntity.badRequest().body("Failed to fetch booking status");
        }
    }

    // Inner classes for request/response
    public static class BookingRequest {
        private String customerName;
        private String customerEmail;
        private String flightDetails;
        private BigDecimal amount;
        private String paymentMethod;

        // Getters and setters
        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }
        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public String getFlightDetails() { return flightDetails; }
        public void setFlightDetails(String flightDetails) { this.flightDetails = flightDetails; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    }

    public static class BookingResponse {
        private String bookingId;
        private String status;
        private String message;

        public BookingResponse(String bookingId, String status, String message) {
            this.bookingId = bookingId;
            this.status = status;
            this.message = message;
        }

        // Getters
        public String getBookingId() { return bookingId; }
        public String getStatus() { return status; }
        public String getMessage() { return message; }
    }

    public static class BookingStatusResponse {
        private String bookingId;
        private String status;

        public BookingStatusResponse(String bookingId, String status) {
            this.bookingId = bookingId;
            this.status = status;
        }

        // Getters
        public String getBookingId() { return bookingId; }
        public String getStatus() { return status; }
    }
}
