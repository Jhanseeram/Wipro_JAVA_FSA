package com.wipro.service;

import com.wipro.dto.PaymentRequest;
import com.wipro.dto.PaymentResponse;
import com.wipro.payment.entity.PaymentTransaction;
import com.wipro.payment.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingService.class);
    private final Random random = new Random();

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        logger.info("Processing payment for booking: {} with amount: {} using method: {}", 
                   paymentRequest.getBookingId(), paymentRequest.getAmount(), paymentRequest.getPaymentMethod());

        try {
            // Simulate payment processing time
            Thread.sleep(1000 + random.nextInt(2000)); // 1-3 seconds

            // Generate payment ID
            String paymentId = "PAY_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // Simulate payment success/failure (90% success rate)
            boolean isSuccessful = random.nextDouble() < 0.9;

            PaymentResponse response;
            PaymentTransaction paymentTransaction;

            if (isSuccessful) {
                response = new PaymentResponse(
                    paymentRequest.getBookingId(),
                    paymentId,
                    "SUCCESS",
                    paymentRequest.getAmount(),
                    "Payment processed successfully"
                );

                // Save successful payment to database
                paymentTransaction = new PaymentTransaction(
                    paymentId,
                    paymentRequest.getBookingId(),
                    paymentRequest.getCustomerName(),
                    paymentRequest.getCustomerEmail(),
                    paymentRequest.getAmount(),
                    paymentRequest.getPaymentMethod(),
                    "SUCCESS",
                    "Payment processed successfully",
                    "****" // Card details not available in PaymentRequest
                );

                logger.info("Payment successful for booking: {} with payment ID: {}", 
                           paymentRequest.getBookingId(), paymentId);
            } else {
                response = new PaymentResponse(
                    paymentRequest.getBookingId(),
                    paymentId,
                    "FAILED",
                    paymentRequest.getAmount(),
                    "Payment failed due to insufficient funds or invalid card details"
                );

                // Save failed payment to database
                paymentTransaction = new PaymentTransaction(
                    paymentId,
                    paymentRequest.getBookingId(),
                    paymentRequest.getCustomerName(),
                    paymentRequest.getCustomerEmail(),
                    paymentRequest.getAmount(),
                    paymentRequest.getPaymentMethod(),
                    "FAILED",
                    "Payment failed due to insufficient funds or invalid card details",
                    "****" // Card details not available in PaymentRequest
                );

                logger.warn("Payment failed for booking: {}", paymentRequest.getBookingId());
            }

            // Save payment transaction to database
            PaymentTransaction savedTransaction = paymentRepository.save(paymentTransaction);
            logger.info("Payment transaction saved to database with ID: {}", savedTransaction.getId());

            return response;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Payment processing interrupted for booking: {}", paymentRequest.getBookingId(), e);
            
            // Save interrupted payment to database
            saveFailedPayment(paymentRequest, "Payment processing was interrupted");
            
            return new PaymentResponse(
                paymentRequest.getBookingId(),
                null,
                "FAILED",
                paymentRequest.getAmount(),
                "Payment processing was interrupted"
            );
        } catch (Exception e) {
            logger.error("Error processing payment for booking: {}", paymentRequest.getBookingId(), e);
            
            // Save error payment to database
            saveFailedPayment(paymentRequest, "Payment processing failed: " + e.getMessage());
            
            return new PaymentResponse(
                paymentRequest.getBookingId(),
                null,
                "FAILED",
                paymentRequest.getAmount(),
                "Payment processing failed: " + e.getMessage()
            );
        }
    }

    private void saveFailedPayment(PaymentRequest paymentRequest, String errorMessage) {
        try {
            String paymentId = "PAY_ERROR_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            PaymentTransaction failedTransaction = new PaymentTransaction(
                paymentId,
                paymentRequest.getBookingId(),
                paymentRequest.getCustomerName(),
                paymentRequest.getCustomerEmail(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentMethod(),
                "FAILED",
                errorMessage,
                "****" // Card details not available in PaymentRequest
            );
            paymentRepository.save(failedTransaction);
            logger.info("Failed payment transaction saved to database with ID: {}", paymentId);
        } catch (Exception ex) {
            logger.error("Failed to save failed payment transaction to database", ex);
        }
    }
}
