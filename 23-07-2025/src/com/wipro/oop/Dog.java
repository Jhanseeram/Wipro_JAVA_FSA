package com.wipro.oop;

public class Dog extends Animal {

	 public Dog(String name, int noOfLegs, String whereDoesItLive) {
	        this.noOfLegs = noOfLegs;
	        this.whereDoesItLive = whereDoesItLive;
	    }
	
	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("Dog: Barks & Lives in:" + whereDoesItLive);
	}

}
