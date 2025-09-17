package com.wipro.service;

import com.wipro.dto.PaymentRequest;
import com.wipro.entity.Booking;
import com.wipro.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private PaymentProducerService paymentProducerService;
    
    @Autowired
    private BookingRepository bookingRepository;

    public String createBookingAndInitiatePayment(String customerName, String customerEmail, 
                                                 String flightDetails, BigDecimal amount, 
                                                 String paymentMethod) {
        
        // Generate booking ID
        String bookingId = "BK_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        logger.info("Creating booking: {} for customer: {} with amount: {}", 
                   bookingId, customerName, amount);

        try {
            // Create booking in database
            saveBookingToDatabase(bookingId, customerName, customerEmail, flightDetails, amount);

            // Create payment request for Kafka
            PaymentRequest paymentRequest = new PaymentRequest(
                bookingId,
                amount,
                paymentMethod,
                customerEmail,
                customerName,
                flightDetails
            );

            // Send payment request to Kafka
            logger.info("Sending payment request to Kafka for booking: {}", bookingId);
            paymentProducerService.sendPaymentRequest(paymentRequest);

            logger.info("Booking created successfully with Kafka integration for booking: {}", bookingId);
            return bookingId;

        } catch (Exception e) {
            logger.error("Error creating booking: {}", bookingId, e);
            throw new RuntimeException("Failed to create booking and initiate payment", e);
        }
    }

    private void saveBookingToDatabase(String bookingId, String customerName, String customerEmail, 
                                     String flightDetails, BigDecimal amount) {
        try {
            // Create booking entity with proper mapping
            Booking booking = new Booking();
            booking.setBookingId(bookingId);
            booking.setPassengerName(customerName);
            booking.setPassengerEmail(customerEmail);
            booking.setPassengerPhone("1234567890"); // Default phone - should come from frontend
            booking.setPassengerAge(25); // Default age - should come from frontend
            booking.setFlightId(1L); // Default flight ID - should come from frontend
            booking.setTravelDate(java.time.LocalDate.now().plusDays(1)); // Default travel date
            booking.setAmount(amount.doubleValue());
            booking.setPaymentMethod("CREDIT_CARD");
            booking.setStatus(Booking.BookingStatus.INITIATED);
            booking.setPaymentStatus(Booking.PaymentStatus.PENDING);
            
            // Generate PNR
            String pnr = "PNR" + System.currentTimeMillis();
            booking.setPnr(pnr);
            
            // Save to database
            bookingRepository.save(booking);
            
            logger.info("Successfully saved booking to database: {} for customer: {} with PNR: {}", 
                       bookingId, customerName, pnr);
        } catch (Exception e) {
            logger.error("Failed to save booking to database: {}", bookingId, e);
            throw new RuntimeException("Database save failed", e);
        }
    }

    public String getBookingStatus(String bookingId) {
        try {
            // Lookup booking in database
            return bookingRepository.findByBookingId(bookingId)
                .map(booking -> booking.getStatus().toString())
                .orElse("NOT_FOUND");
        } catch (Exception e) {
            logger.error("Error fetching booking status for: {}", bookingId, e);
            return "ERROR";
        }
    }
    
    // Helper method to update booking status
    private void updateBookingStatus(String bookingId, Booking.BookingStatus bookingStatus, Booking.PaymentStatus paymentStatus) {
        try {
            bookingRepository.findByBookingId(bookingId).ifPresent(booking -> {
                booking.setStatus(bookingStatus);
                booking.setPaymentStatus(paymentStatus);
                booking.setUpdatedAt(java.time.LocalDateTime.now());
                bookingRepository.save(booking);
                logger.info("Updated booking status for {}: {} - {}", bookingId, bookingStatus, paymentStatus);
            });
        } catch (Exception e) {
            logger.error("Failed to update booking status for: {}", bookingId, e);
        }
    }
}
