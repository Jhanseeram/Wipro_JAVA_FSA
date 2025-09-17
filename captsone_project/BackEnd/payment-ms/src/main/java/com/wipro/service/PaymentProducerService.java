package com.wipro.service;

import com.wipro.dto.PaymentResponse;
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
    private static final String PAYMENT_RESPONSE_TOPIC = "payment-response";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentResponse(PaymentResponse paymentResponse) {
        try {
            logger.info("Sending payment response to Kafka topic: {} for booking: {}", 
                       PAYMENT_RESPONSE_TOPIC, paymentResponse.getBookingId());
            
            CompletableFuture<SendResult<String, Object>> future = 
                kafkaTemplate.send(PAYMENT_RESPONSE_TOPIC, paymentResponse.getBookingId(), paymentResponse);
            
            future.whenComplete((result, exception) -> {
                if (exception == null) {
                    logger.info("Payment response sent successfully for booking: {} with offset: {}", 
                               paymentResponse.getBookingId(), result.getRecordMetadata().offset());
                } else {
                    logger.error("Failed to send payment response for booking: {}", 
                               paymentResponse.getBookingId(), exception);
                }
            });
            
        } catch (Exception e) {
            logger.error("Error sending payment response to Kafka for booking: {}", 
                        paymentResponse.getBookingId(), e);
            throw new RuntimeException("Failed to send payment response", e);
        }
    }
}
