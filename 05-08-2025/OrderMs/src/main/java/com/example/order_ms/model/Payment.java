package com.example.order_ms.model;

import lombok.Data;

@Data
public class Payment {
    int id;
    int orderId;
    boolean paymentStatus;
    double paymentAmount;
}

