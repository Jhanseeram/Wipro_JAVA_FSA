package com.example.order_ms.dto;

import com.example.order_ms.model.Payment;
import lombok.Data;
import java.util.List;

@Data
public class OrderResponse {
    String orderNumber;
    double orderValue;
    List<Payment> payment;
}
