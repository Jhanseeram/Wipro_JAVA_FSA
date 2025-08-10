package com.Wipro.oops;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

	public static int getAge(LocalDate dt)
	{
		int birthYear = dt.getYear();
		int diff = 0 ;
		return diff;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LocalDate birthDate = LocalDate.of(2002, 8, 6);
		int age = getAge(birthDate);
		System.out.println(age);	
	}
}
