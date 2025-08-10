package com.wipro.anonymous;

public class AnonymousInnerDemo {

	public static void main(String[] args) {
		BankOps savingsAccount = new BankOps () {

			@Override
			public void deposit(double amount) {
				System.out.println("Saving " + amount);
				
			}
			
		};
		savingsAccount.deposit(10000);
		
		BankOps currentAccount = new BankOps () {

			@Override
			public void deposit(double amount) {
				System.out.println("Current Account " + amount);
				
			}
			
		};
		currentAccount.deposit(50000);

	}


}
