package com.wipro.oop;

public class Gpay implements PaymentMethod{

	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("GPay"+amount);
	}

}
