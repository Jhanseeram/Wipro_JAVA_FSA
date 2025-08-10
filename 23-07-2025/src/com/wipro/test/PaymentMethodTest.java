package com.wipro.test;

import com.wipro.oop.Gpay;
import com.wipro.oop.PhonePay;

public class PaymentMethodTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Gpay g = new Gpay();
		g.pay(1000.00);
		
		PhonePay p = new PhonePay();
		p.pay(50000.0);
	}

}
