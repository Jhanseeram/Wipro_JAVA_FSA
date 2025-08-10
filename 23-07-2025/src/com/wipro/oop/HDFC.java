package com.wipro.oop;

public class HDFC implements BankOps {
    private double balance = 1000; 

    @Override
    public void deposit(double amount, String accNumber) 
    {
    	balance += amount;
        System.out.println("HDFC Balance: "+balance+"AccNo: "+accNumber );
    }

    @Override
    public double withdraw(double amount, String accNumber) 
    {
    	balance -= amount;
        System.out.println("HDFC Withdrawn " + amount + "Acc No " + accNumber + " New balance: " + balance);
        return amount;
    }
}
