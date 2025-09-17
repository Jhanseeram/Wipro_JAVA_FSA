package com.wipro.service;

import com.wipro.dto.PaymentRequest;
import com.wipro.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumerService.class);

    @Autowired
    private PaymentProcessingService paymentProcessingService;

    @Autowired
    private PaymentProducerService paymentProducerService;

    @KafkaListener(topics = "payment-request", groupId = "payment-group")
    public void handlePaymentRequest(PaymentRequest paymentRequest) {
        
        if (paymentRequest == null) {
            logger.error("Received null paymentRequest");
            return;
        }
        
        try {
            logger.info("📥 Received payment request from Kafka for booking: {}", paymentRequest.getBookingId());
            logger.info("Processing payment with amount: {} using method: {}", 
                       paymentRequest.getAmount(), paymentRequest.getPaymentMethod());

            // Process payment and save to database
            PaymentResponse paymentResponse = paymentProcessingService.processPayment(paymentRequest);

            // Send response back to booking service
            paymentProducerService.sendPaymentResponse(paymentResponse);
            
            logger.info("✅ Successfully processed payment for booking: {} with status: {}", 
                       paymentRequest.getBookingId(), paymentResponse.getStatus());

        } catch (Exception e) {
            logger.error("❌ Error processing payment request for booking: {}", 
                        paymentRequest.getBookingId(), e);
            
            // Send failure response
            PaymentResponse failureResponse = new PaymentResponse();
            failureResponse.setBookingId(paymentRequest.getBookingId());
            failureResponse.setPaymentId(null);
            failureResponse.setStatus("FAILED");
            failureResponse.setAmount(paymentRequest.getAmount());
            failureResponse.setMessage("Payment processing failed: " + e.getMessage());
            
            try {
                paymentProducerService.sendPaymentResponse(failureResponse);
            } catch (Exception sendException) {
                logger.error("Failed to send failure response for booking: {}", 
                           paymentRequest.getBookingId(), sendException);
            }
        }
    }
}
