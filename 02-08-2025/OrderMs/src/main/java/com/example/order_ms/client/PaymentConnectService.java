package com.example.order_ms.client;

import com.example.order_ms.model.Payment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentConnectService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<Payment> savePaymentData(Payment payment) {
        String url = "http://localhost:9011/payments";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Payment> request = new HttpEntity<>(payment, headers);
        return restTemplate.postForEntity(url, request, Payment.class);
    }
}
