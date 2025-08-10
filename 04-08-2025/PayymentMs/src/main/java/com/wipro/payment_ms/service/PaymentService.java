package com.wipro.payment_ms.service;

import java.util.List;
import java.util.Optional;

import com.wipro.payment_ms.entity.Payment;

public interface PaymentService {
//	Payment save(Payment payment);
	void save(Payment order);
	List<Payment> findAll();
	Optional<Payment> findById(Integer id);
	boolean update(Integer id, Payment updatedPayment);
	boolean delete(Integer id);
}
