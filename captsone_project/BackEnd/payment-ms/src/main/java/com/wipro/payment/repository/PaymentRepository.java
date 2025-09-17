package com.wipro.payment.repository;

import com.wipro.payment.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentTransaction, Long> {
    
    Optional<PaymentTransaction> findByPaymentId(String paymentId);
    
    List<PaymentTransaction> findByBookingId(String bookingId);
    
    List<PaymentTransaction> findByStatus(String status);
    
    List<PaymentTransaction> findByCustomerEmail(String customerEmail);
}