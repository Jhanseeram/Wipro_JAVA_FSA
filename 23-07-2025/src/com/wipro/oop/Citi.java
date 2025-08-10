package com.wipro.oop;

public class Citi implements BankOps 
{
    private double balance = 2000; 

    @Override
    public void deposit(double amount, String accNumber) 
    {
        balance += amount;
        System.out.println("CITI Balance: "+ balance +"AccNo: "+accNumber );
    }

    @Override
    public double withdraw(double amount, String accNumber) 
    {
        balance -= amount;
        System.out.println("CITI Withdrawn " + amount + "Acc No " + accNumber + " New balance: " + balance);
        return amount;
    }
}
