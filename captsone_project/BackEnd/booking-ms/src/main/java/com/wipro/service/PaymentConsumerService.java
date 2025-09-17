package com.wipro.service;

import com.wipro.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumerService.class);

    @KafkaListener(topics = "payment-response", groupId = "booking-group")
    public void handlePaymentResponse(@Payload PaymentResponse paymentResponse,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                    @Header(KafkaHeaders.OFFSET) long offset,
                                    Acknowledgment acknowledgment) {
        try {
            logger.info("Received payment response from topic: {} [{}] at offset {} for booking: {}", 
                       topic, partition, offset, paymentResponse.getBookingId());

            // Process payment response
            processPaymentResponse(paymentResponse);

            // Acknowledge the message
            acknowledgment.acknowledge();
            
            logger.info("Successfully processed payment response for booking: {} with status: {}", 
                       paymentResponse.getBookingId(), paymentResponse.getStatus());

        } catch (Exception e) {
            logger.error("Error processing payment response for booking: {}", 
                        paymentResponse.getBookingId(), e);
            // In production, you might want to send to a dead letter queue
        }
    }

    private void processPaymentResponse(PaymentResponse paymentResponse) {
        // Update booking status based on payment response
        String bookingId = paymentResponse.getBookingId();
        String status = paymentResponse.getStatus();

        switch (status) {
            case "SUCCESS":
                logger.info("Payment successful for booking: {}. Confirming booking...", bookingId);
                // Update booking status to CONFIRMED
                updateBookingStatus(bookingId, "CONFIRMED");
                // Send confirmation email, etc.
                break;
                
            case "FAILED":
                logger.warn("Payment failed for booking: {}. Cancelling booking...", bookingId);
                // Update booking status to CANCELLED
                updateBookingStatus(bookingId, "CANCELLED");
                // Send failure notification, etc.
                break;
                
            case "PENDING":
                logger.info("Payment pending for booking: {}. Keeping booking as PENDING...", bookingId);
                // Keep booking status as PENDING
                updateBookingStatus(bookingId, "PENDING");
                break;
                
            default:
                logger.warn("Unknown payment status '{}' for booking: {}", status, bookingId);
        }
    }

    private void updateBookingStatus(String bookingId, String status) {
        // TODO: Implement booking status update logic
        // This would typically update the booking entity in the database
        logger.info("Updated booking {} status to: {}", bookingId, status);
    }
}
