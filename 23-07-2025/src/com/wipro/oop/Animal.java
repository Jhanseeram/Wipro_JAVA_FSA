package com.wipro.oop;

public abstract class Animal {
	int noOfLegs;
	String whereDoesItLive;
	
	public void eat() {
        System.out.println(" is eating.");
    }

    public abstract void makeSound();
}
