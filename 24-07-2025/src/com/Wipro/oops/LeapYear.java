package com.Wipro.oops;

public class LeapYear {
	
	public static void main(String[] args)
	{
		int year = 2024;
		boolean isLeapYear = false;
		
		if (year % 4 == 0) {
		    if (year % 100 == 0) {
		        if (year % 400 == 0) {
		            isLeapYear = true;
		        }
		    } else {
		        isLeapYear = true;
		    }
		}
	
	if (isLeapYear) {
	    System.out.println(year + " is a leap year.");
	} else {
	    System.out.println(year + " is not a leap year.");
	    }
	}
}
