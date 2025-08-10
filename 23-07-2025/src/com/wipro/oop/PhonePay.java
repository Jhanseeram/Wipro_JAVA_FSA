package com.wipro.oop;

public class PhonePay implements PaymentMethod {

	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("PhonePay"+amount);
	}
	
	
}
