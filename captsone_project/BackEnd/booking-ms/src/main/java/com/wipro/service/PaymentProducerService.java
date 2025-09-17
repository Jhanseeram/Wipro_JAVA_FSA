package com.wipro.service;

import com.wipro.dto.PaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProducerService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProducerService.class);
    private static final String PAYMENT_REQUEST_TOPIC = "payment-request";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentRequest(PaymentRequest paymentRequest) {
        try {
            logger.info("Sending payment request to Kafka topic: {} for booking: {}", 
                       PAYMENT_REQUEST_TOPIC, paymentRequest.getBookingId());
            
            CompletableFuture<SendResult<String, Object>> future = 
                kafkaTemplate.send(PAYMENT_REQUEST_TOPIC, paymentRequest.getBookingId(), paymentRequest);
            
            future.whenComplete((result, exception) -> {
                if (exception == null) {
                    logger.info("Payment request sent successfully for booking: {} with offset: {}", 
                               paymentRequest.getBookingId(), result.getRecordMetadata().offset());
                } else {
                    logger.error("Failed to send payment request for booking: {}", 
                               paymentRequest.getBookingId(), exception);
                }
            });
            
        } catch (Exception e) {
            logger.error("Error sending payment request to Kafka for booking: {}", 
                        paymentRequest.getBookingId(), e);
            throw new RuntimeException("Failed to send payment request", e);
        }
    }
}
