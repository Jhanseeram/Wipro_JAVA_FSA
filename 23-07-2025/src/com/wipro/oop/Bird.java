package com.wipro.oop;

public class Bird extends Animal {
	
	public Bird(String name, int noOfLegs, String whereDoesItLive) {
        this.noOfLegs = noOfLegs;
        this.whereDoesItLive = whereDoesItLive;
    }
	
	public void eat() {
        System.out.println(" is eating.");
    }
	
	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("Birds - Chrip");
	}

}
