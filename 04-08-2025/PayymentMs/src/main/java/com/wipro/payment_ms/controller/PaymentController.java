package com.wipro.payment_ms.controller;

//import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.payment_ms.entity.Payment;
import com.wipro.payment_ms.service.PaymentService;

@RestController
@RequestMapping("/payment") 

public class PaymentController {
	@Autowired
	PaymentService paymentService;
    
	@PostMapping
	void save(@RequestBody Payment payment)
	{
		paymentService.save(payment);	
	}
	
    @GetMapping
	List<Payment> findAll()
	{
		return paymentService.findAll();
	}
    
    
    
    
    
    
    
    
    
    
    
//    @PostMapping
//	public ResponseEntity<String> save(@RequestBody Payment payment) {
//	    payment.setPaymentStatus(true);
//	    paymentService.save(payment);
//	    
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.add("created-at", LocalDate.now().toString());
//
//	    return ResponseEntity
//	            .status(HttpStatus.CREATED)
//	            .headers(headers)
//	            .body("Data Saved Successfully");
//	}
//	
//	@GetMapping
//	ResponseEntity<?> findAll()
//	{
//		List<Payment> list = paymentService.findAll();
//		
//		HttpHeaders headers = new HttpHeaders();
//        headers.add("search-time", LocalDateTime.now().toString());
//		
//        if (list.isEmpty()) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .headers(headers)
//                    .body("No payments found");
//        } else {
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .body(list);
//        }
//	}
//	
//	@GetMapping("/{id}")
//    public ResponseEntity<?> getPaymentById(@PathVariable int id) {
//        Optional<Payment> payment = paymentService.findById(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("search-time", LocalDateTime.now().toString());
//
//        if (payment.isPresent()) {
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .body(payment.get());
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .headers(headers)
//                    .body("Payment Not Found");
//        }
//    }
//	
//	@PutMapping("/{id}")
//    public ResponseEntity<String> updatePayment(@PathVariable int id, @RequestBody Payment updatedPayment) {
//        boolean updated = paymentService.update(id, updatedPayment);
//
//        if (updated) {
//            return ResponseEntity
//                    .ok("Payment Data Modified Successfully");
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body("Payment Not Found");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletePayment(@PathVariable int id) {
//        boolean deleted = paymentService.delete(id);
//
//        if (deleted) {
//            return ResponseEntity
//                    .ok("Payment Data Deleted Successfully");
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body("Payment Not Found");
//        }
//    }
}
