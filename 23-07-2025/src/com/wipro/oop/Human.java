package com.wipro.oop;

public class Human extends Animal {

	public Human(int noOfLegs, String whereDoesItLive) {
        this.noOfLegs = noOfLegs;
        this.whereDoesItLive = whereDoesItLive;
    }
	
	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("Hello");
	}

	
}
