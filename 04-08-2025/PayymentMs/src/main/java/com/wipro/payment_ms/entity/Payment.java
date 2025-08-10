package com.wipro.payment_ms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="payment")
@Data
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; 
	
	@Column(name="payment_amount")
    double paymentAmount;
	
	@Column(name = "order_id")
	int orderId;

	@Column(name = "payment_status")
	boolean paymentStatus;
}
