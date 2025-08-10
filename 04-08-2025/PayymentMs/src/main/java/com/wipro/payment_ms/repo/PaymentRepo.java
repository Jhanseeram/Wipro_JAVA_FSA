package com.wipro.payment_ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.payment_ms.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
